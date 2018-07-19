package com.carcompare.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 缓存工具类
 */
@Component
public class CacheUtil {

    @Autowired
    CacheManager cacheManager;

    /**
     * 默认缓存
     */
    public static String APP_CACHE = "app";

    /**
     * 用户在线缓存
     */
    public static String USER_ONLINE_CACHE = "user_online";

    /**
     * 设置缓存
     * @param key
     * @param value
     * @param expire 过期时间（分钟）
     */
    public void setCache(String cacheName, String key, Object value, int expire){
        Cache cache = cacheManager.getCache(cacheName);
        Element element = new Element(key, value);
        element.setTimeToIdle(expire);
        element.setTimeToLive(expire);
        try
        {
            cache.acquireWriteLockOnKey(key);
            cache.put(element);
        }finally {
            cache.releaseWriteLockOnKey(key);
        }
    }

    /**
     * 获取缓存
     * @param key
     */
    public Object getCache(String cacheName, String key){
        Cache cache = cacheManager.getCache(cacheName);
        Element element = cache.get(key);
        return  element != null ? element.getObjectValue() : null;
    }

    /**
     * 清除缓存
     * @param key
     */
    public boolean  removeCache(String cacheName, String key){
        Cache cache = cacheManager.getCache(cacheName);
        return cache.remove(key);
    }
}
