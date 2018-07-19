package com.carcompare.core.authentication;

import com.carcompare.base.UserFriendlyException;
import com.carcompare.controller.AuthController;
import com.carcompare.core.users.User;
import com.carcompare.shiro.jwt.JwtToken;
import com.carcompare.utils.TokenUtil;
import com.carcompare.websocket.MessageSender;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountManager {
    public static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    TokenManager tokenManager;

    @Autowired
    MessageSender notifier;

    @Autowired
    TokenUtil tokenUtil;

    /**
     * 登录
     * @param user
     * @param jwtToken
     * @return
     */
    public Boolean login(User user, JwtToken jwtToken){
        String lastToken = tokenManager.getCacheToken(user.getId().toString());

        tokenManager.setCacheToken(user.getId().toString(), jwtToken.getToken());

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(jwtToken);

            if(lastToken != null){
                notifier.notifyKickout(lastToken.toString());
            }
        }
        catch (Exception ex){
            if(ex.getCause() instanceof UserFriendlyException){
                throw (UserFriendlyException)ex.getCause();
            }

            logger.error(ex.toString());

            throw new UserFriendlyException("登录失败");
        }

        return subject.isAuthenticated();
    }

    /**
     * 退出
     */
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().stop();
        subject.logout();
    }

    /**
     * 更新在线缓存
     * @param userId
     * @param newToken
     */
    public void refreshToken(Object userId, String newToken){
        tokenManager.setCacheToken(userId.toString() ,newToken);
    }

    /**
     * 是否被踢下线
     * @param userId
     * @param token
     * @return
     */
    public boolean isKickout(String userId, String token){
        String cacheToken = tokenManager.getCacheToken(userId);

        if(cacheToken == null){
            return false;
        }

        Claims cacheTokenClaims = getTokenClaims(cacheToken);
        Claims tokenClaims = getTokenClaims(token);

        if(cacheTokenClaims == null || tokenClaims == null){
            return false;
        }

        Object cacheTokenCreated = this.getClaimValue(cacheTokenClaims, TokenUtil.CLAIM_KEY_CREATED);
        Object tokenCreated = this.getClaimValue(tokenClaims, TokenUtil.CLAIM_KEY_CREATED);

        if(!cacheTokenCreated.toString().equals(tokenCreated.toString()) && !cacheToken.equals(token)){
            return true;
        }

        return false;
    }

    private Claims getTokenClaims(String token){
        Claims claims;
        try {
            claims = tokenUtil.getClaims(token);
        }catch (Exception ex){
            return null;
        }

        return claims;
    }

    private Object getClaimValue(Claims claims, String key) {
        return claims.get(key);
    }
}