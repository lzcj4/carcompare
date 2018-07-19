package com.carcompare.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.security.MessageDigest;

public class AppUtil{
    static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

    public static void ResponseApiResult(HttpServletResponse response, int httpStatus, String json){
        try{
            response.reset();
            response.setStatus(httpStatus);
            response.setContentType("application/json;charset=UTF-8");
            OutputStream output = response.getOutputStream();
            output.write(json.getBytes("utf-8"));
            output.flush();
            output.close();
        }catch (Exception ex){
            ex.printStackTrace();
            //logger
        }
    }

    public static String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes).toLowerCase();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    public static String getToken(HttpServletRequest httpServletRequest){
        String JWT_BEARER_FLAG = "Bearer ";

        String authorization = httpServletRequest.getHeader("authorization");
        if (StringUtils.isEmpty(authorization) || !authorization.startsWith(JWT_BEARER_FLAG)) {
            return "";
        }

        return authorization.substring(7);
    }

    /**
     * 获取类路径
     * @return
     */
    public static String getClassPath(){
        try {
            return ResourceUtils.getURL("classpath:").toURI().getPath();
        }catch (Exception ex){
            return "";
        }
    }
}

