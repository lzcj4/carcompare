package com.carcompare.license;

import com.carcompare.utils.Base64Util;
import org.springframework.stereotype.Component;

/**
 * 编码器
 */
@Component
public class Encoder {
    /**
     * 字符集名称
     */
    static final String CHARSET_NAME = "utf-8";

    /**
     * 加密
     * @param plainText
     * @return
     * @throws Exception
     */
    public String encode(String plainText, String privateKey) throws Exception{
        try {
            byte[] dataByteArray = plainText.getBytes(CHARSET_NAME);

            byte[] encryptedDataByteArray = RSAUtil.encryptByPrivateKey(dataByteArray, privateKey);
            String encryptedText = Base64Util.encode(encryptedDataByteArray);

            return encryptedText;
        }catch (Exception ex){
            throw  ex;
        }
    }
}
