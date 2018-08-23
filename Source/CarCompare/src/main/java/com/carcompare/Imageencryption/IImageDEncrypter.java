package com.carcompare.Imageencryption;

/**
 * 图片加解密接口
 */
public interface IImageDEncrypter {
    /**
     * 加密
     * @param bytes
     * @return
     */
    byte[] encrypt(byte[] bytes);

    /**
     * 解密
     * @param bytes
     * @return
     */
    byte[] decrypt(byte[] bytes);
}
