package com.carcompare.license;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 许可证信息
 */
@Data
public class LicenseInfo {
    /**
     * 机器码
     */
    private String machineCode;

    /**
     * 授权开始时间
     */
    private Date begin;

    /**
     * 授权结束时间
     */
    private Date end;

    /**
     * 授权注册用户数
     */
    private int userCount;

    public String getBeginFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(begin);
    }

    public String getEndFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end);
    }

    /**
     * 转换成JSON字符串
     * @return
     */
    public String toJSONString(){
        return JSON.toJSONString(this);
    }

    /**
     * 从JSON字符串转换成对象
     * @param text
     */
    public void fromJSONString(String text){
        JSONObject jsonObject = JSON.parseObject(text);
        LicenseInfo licenseInfo = JSONObject.toJavaObject(jsonObject, LicenseInfo.class);
        BeanUtils.copyProperties(licenseInfo, this);
    }
}
