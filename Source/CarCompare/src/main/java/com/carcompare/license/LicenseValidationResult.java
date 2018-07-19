package com.carcompare.license;

import lombok.Data;

/**
 * 许可证验证结果
 */
@Data
public class LicenseValidationResult {
    /**
     * 证可证信息
     */
    LicenseInfo licenseInfo;

    /**
     * 服务器信息
     */
    ServerInfo serverInfo;

    /**
     * 是否有效的
     */
    Boolean valid;

    /**
     * 验证信息（用于记录验证失效时的相关信息）
     */
    String message;

    public LicenseValidationResult(){
        this.valid = false;
    }
}
