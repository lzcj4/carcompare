package com.carcompare.license;

import com.carcompare.utils.Base64Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 解码器
 */
@Component
class Decoder {
    static final Logger logger = LoggerFactory.getLogger(Decoder.class);

    static final String CHARSET_NAME = "utf-8";

    /**
     * 解码
     * @param encryptedText
     * @param publicKey
     * @return
     * @throws Exception
     */
    public String decode(String encryptedText, String publicKey) throws Exception{
        try {
            byte[] data = Base64Util.decode(encryptedText);
            byte[] decryptedDataByteArray = RSAUtil.decryptByPublicKey(data, publicKey);
            String decryptedText = new String(decryptedDataByteArray, CHARSET_NAME);

            return decryptedText;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
