package com.carcompare.license;

import com.carcompare.utils.AppUtil;
import com.carcompare.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * License信息提供类
 */
@Component
class LicenseInfoProvider {
    static final Logger logger = LoggerFactory.getLogger(LicenseInfoProvider.class);

    static final String FILE_NAME = "license.lrc";

    @Autowired
    Decoder decoder;

    @Autowired
    IRSAKeyProvider rsaKeyProvider;

    LicenseInfo licenseInfo;

    public LicenseInfo getLicenseInfo(){
        if(licenseInfo != null){
            return licenseInfo;
        }

        String publicKey = this.rsaKeyProvider.getPublicKey();
        if(publicKey == null){
            return null;
        }

        String encryptedText = this.readFile();
        if(encryptedText == null){
            return null;
        }

        try
        {
            String decryptedText = decoder.decode(encryptedText, publicKey);
            if(decryptedText == null){
                return null;
            }

            licenseInfo = new LicenseInfo();
            licenseInfo.fromJSONString(decryptedText);

            return licenseInfo;
        }catch (Exception ex){
            logger.error(ex.toString());
            return null;
        }
    }

    /**
     * 读取许可信息
     * @return
     */
    private String readFile(){
        String file = getFile();
        if(!FileUtil.fileExists(file)){
            logger.error("许可证文件未找到");
            return null;
        }

        return FileUtil.readFile(getFile());
    }

    /**
     * 获取文件
     * @return
     */
    private String getFile(){
        return AppUtil.getClassPath().concat("/").concat(FILE_NAME);
    }
}
