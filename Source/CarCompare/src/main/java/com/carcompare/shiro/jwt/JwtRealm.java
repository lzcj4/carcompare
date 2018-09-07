package com.carcompare.shiro.jwt;

import com.carcompare.core.authentication.AccountManager;
import com.carcompare.base.UserFriendlyException;
import com.carcompare.core.menus.Menu;
import com.carcompare.core.users.User;
import com.carcompare.service.UserService;
import com.carcompare.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    AccountManager accountManager;

    @Override
    public boolean supports(AuthenticationToken token) {
        //表示此Realm只支持JwtToken类型
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 根据用户名查找角色，请根据需求实现
        String username = (String) principals.getPrimaryPrincipal();

        User user = userService.getUser(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (user != null) {
            authorizationInfo.addRole(user.getRole().getCode());
            for (Menu menu : user.getRole().getMenus()) {
                authorizationInfo.addStringPermission(menu.getRoleName());
            }
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken == null) {
            throw new UserFriendlyException("认证失败！");
        }

        JwtToken jwtToken = (JwtToken) authenticationToken;
        String token = jwtToken.getToken();

        Claims claims;
        try {
            claims = tokenUtil.getClaims(token);
        } catch (ExpiredJwtException e) {
            throw new UserFriendlyException("认证失败，登录已过期！");
        }catch (Exception ex){
            throw new UserFriendlyException("认证失败！");
        }

        if (claims == null) {
            throw new UserFriendlyException("认证失败！");
        }

        Object userId = this.getClaimValue(claims, TokenUtil.CLAIM_KEY_USERID);
        Object username = this.getClaimValue(claims, TokenUtil.CLAIM_KEY_USERNAME);
        Object isBackend = this.getClaimValue(claims, TokenUtil.CLAIM_KEY_USER_IS_BACKEND);

        this.isKickout(userId.toString(), token);

        this.setSession(userId, username, isBackend);

        return new SimpleAuthenticationInfo(username, token, getName());
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    private Object getClaimValue(Claims claims, String key) {
        return claims.get(key);
    }

    private void setSession(Object userId, Object username, Object isBackend) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute(TokenUtil.CLAIM_KEY_USERID, userId);
        subject.getSession().setAttribute(TokenUtil.CLAIM_KEY_USERNAME, username);
        subject.getSession().setAttribute(TokenUtil.CLAIM_KEY_USER_IS_BACKEND, isBackend);
    }

    private void isKickout(String userId, String token){
        if(this.accountManager.isKickout(userId, token)){
            throw new UserFriendlyException("授权失败，该账号在异地登陆！");
        }
    }
}