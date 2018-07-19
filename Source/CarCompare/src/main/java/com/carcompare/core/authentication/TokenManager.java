package com.carcompare.core.authentication;

import com.carcompare.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenManager {
    @Autowired
    CacheUtil cacheUtil;

    public void setCacheToken(String userId, String token){
        cacheUtil.setCache(CacheUtil.USER_ONLINE_CACHE, userId, token, 0);
    }

    public String getCacheToken(String userId){
        Object value = cacheUtil.getCache(CacheUtil.USER_ONLINE_CACHE, userId);

        if(value == null){
            return null;
        }

        return value.toString();
    }
}
