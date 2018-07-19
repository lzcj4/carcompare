package com.carcompare.license;

import com.carcompare.base.UserFriendlyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 许可证验证器
 */
@Component
public class LicenseValidator {
    static final Logger logger = LoggerFactory.getLogger(LicenseValidator.class);

    LicenseInfo licenseInfo;

    ServerInfo serverInfo;

    LicenseValidationResult licenseValidationResult;

    @Autowired
    ServerTimeManager serverTimeManager;

    @Autowired
    LicenseInfoProvider licenseInfoProvider;

    @Autowired
    ServerInfoProvider serverInfoProvider;

    /**
     * 获取许可证认证结果
     * @return
     */
    public LicenseValidationResult getLicenseValidationResult() {
        return licenseValidationResult;
    }

    /**
     * 验证
     */
    public void validate(){
        if(licenseValidationResult == null){
            licenseInfo = licenseInfoProvider.getLicenseInfo();
            serverInfo = serverInfoProvider.getServerInfo();
            licenseValidationResult = new LicenseValidationResult();
            licenseValidationResult.setLicenseInfo(licenseInfo);
            licenseValidationResult.setServerInfo(serverInfo);
        }
        doValidate();
    }

    /**
     * 执行验证
     * @return
     */
    private void doValidate(){
        if(licenseInfo == null){
            licenseValidationResult.setValid(false);
            licenseValidationResult.setMessage("系统未授权：许可证未找到");
            return;
        }

        //机器码不匹配
        if(!licenseInfo.getMachineCode().equals(serverInfo.getMachineCode())){
            licenseValidationResult.setValid(false);
            licenseValidationResult.setMessage("系统未授权：许可证无效");
            return;
        }

        //许可证时间为空
        if(licenseInfo.getBegin() == null || licenseInfo.getEnd() == null){
            licenseValidationResult.setValid(false);
            licenseValidationResult.setMessage("系统未授权：许可证授权时间不正确");
            return;
        }

        Date currentDate;
        try
        {
            currentDate = serverTimeManager.getServerTime();
        }catch (UserFriendlyException ufex){
            licenseValidationResult.setValid(false);
            licenseValidationResult.setMessage(ufex.getMessage());
            return;
        }

        //系统未授权时间未生效
        if(currentDate.getTime() < licenseInfo.getBegin().getTime()){
            licenseValidationResult.setValid(false);
            licenseValidationResult.setMessage(String.format("系统未授权：授权时间将于%s生效", licenseInfo.getBeginFormat()));
            return;
        }

        //系统未授权时间已过期
        if(currentDate.getTime() > licenseInfo.getEnd().getTime()){
            licenseValidationResult.setValid(false);
            licenseValidationResult.setMessage(String.format("系统未授权：授权时间已于%s过期", licenseInfo.getEndFormat()));
            return;
        }

        licenseValidationResult.setValid(true);
    }
}
