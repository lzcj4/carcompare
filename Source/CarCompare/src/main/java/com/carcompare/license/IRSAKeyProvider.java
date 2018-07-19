package com.carcompare.license;

/**
 * RSA密钥提供接口
 */
public interface IRSAKeyProvider {
    /**
     * 获取公钥
     * @return
     */
    String getPublicKey();
}
