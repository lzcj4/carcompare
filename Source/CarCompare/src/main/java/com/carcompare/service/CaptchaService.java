package com.carcompare.service;

import com.carcompare.utils.CacheUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * 验证码服务
 */
@Service
public class CaptchaService extends BaseService {

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Autowired
    CacheUtil cacheUtil;

    @Value("${custom.captcha.expires}")
    int captchaExpires;

    /**
     *
     * @return
     */
    public String createToken(){
        return UUID.randomUUID().toString();
    }

    /**
     * 生成验证码
     * @return
     */
    public BufferedImage createImage(){
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText().toLowerCase();

            cacheUtil.setCache(CacheUtil.APP_CACHE, createText, createText, captchaExpires);

            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            return challenge;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * 验证
     * @param code
     * @return
     */
    public Boolean validate(String code){
        if(code == null){
            return false;
        }

        Object value = cacheUtil.getCache(CacheUtil.APP_CACHE, code.toLowerCase());
        return  value != null;
    }

    /**
     * 清除
     * @param code
     */
    public void delete(String code){
        cacheUtil.removeCache(CacheUtil.APP_CACHE, code);
    }
}
