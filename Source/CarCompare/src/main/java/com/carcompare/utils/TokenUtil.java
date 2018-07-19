package com.carcompare.utils;

import com.carcompare.core.users.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil  {
    static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * 用户ID
     */
    public static final String CLAIM_KEY_USERID = "userId";
    public static final String CLAIM_KEY_USERNAME = "username";
    public static final String CLAIM_KEY_USER_IS_BACKEND = "is_backend";
    public static final String CLAIM_KEY_AUDIENCE = "audience";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final String CLAIM_KEY_UPDATED = "updated";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    private static final String SECRET = "v3d4e446a7a94da59cb193203116c06f3acff0e258054ea0a7bce87x7e44b27a";

    /**
     * token有效期(秒)
     */
    @Value("${custom.jwt.token.expiration}")
    private Long expiration;

    /**
     * 设定一个时间值（单位秒），当token过期时间小于该值时进行更新
     */
    @Value("${custom.jwt.token.times}")
    private int times;

    /**
     * 生成token
     * @param user 用户
     * @param device  org.springframework.mobile.device 设备判断对象
     * @return
     */
    public String createToken(User user, Device device) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERID, user.getId());
        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_USER_IS_BACKEND, user.getRole().getIsBackend());

        claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_UPDATED, null);
        return createToken(claims);
    }

    /**
     * 刷新token
     * @param claims
     * @return
     */
    public String refreshToken(Claims claims) {
        claims.put(CLAIM_KEY_UPDATED, new Date());
        return createToken(claims);
    }

    /**
     */
    public Claims getClaims(String token) {
        return this.parseToken(token);
    }

    /**
     * token是否即将过期
     * @return
     */
    public boolean canRefresh(Claims claims){
        Date expiryDate = claims.getExpiration();
        Date nowDate = new Date();
        long diff = (expiryDate.getTime() - nowDate.getTime()) / 1000;

        //logger.info(String.format("expiryDate:%s, nowDate:%s", expiryDate, nowDate));

        //已过期,不允许刷新
        if(diff <= 0){
            //logger.info("!canRefresh:" + diff);
            return false;
        }

        //未过期, 过期时间-当前时间 所得的差值 <= 更新阀值 时,则允许更新
        if(diff <= times){
            //logger.info("canRefresh:" + diff);
            return true;
        }

        //logger.info("!canRefresh:" + diff);
        return false;
    }

    /**
     * 创建token
     * @param claims
     * @return
     */
    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    private Claims parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 生成token时间 = 当前时间 + expiration（properties中配置的失效时间）
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 通过spring-mobile-device的device检测访问主体
     * @param device
     * @return
     */
    private  String generateAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;//PC端
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;//平板
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;//手机
        }
        return audience;
    }
}
