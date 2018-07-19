package com.carcompare.license;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 许可证验证调度器
 */
@Component
public class LicenseValidationScheduler {
    @Autowired
    LicenseValidator licenseValidator;

    /**
     * 每15秒钟验证一次
     */
    @Scheduled(fixedDelay = 15000)
    private void validate(){
        licenseValidator.validate();
    }
}
