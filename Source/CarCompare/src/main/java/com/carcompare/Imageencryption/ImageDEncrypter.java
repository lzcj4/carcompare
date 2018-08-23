package com.carcompare.Imageencryption;

import org.springframework.stereotype.Component;

/**
 * 图片加解密码方案二
 */
@Component
public class ImageDEncrypter implements IImageDEncrypter{
    /**
     * 加密
     * @param bytes
     * @return
     */
    @Override
    public byte[] encrypt(byte[] bytes){
        bytes[0] = (byte)~bytes[0];
        return  bytes;
    }

    /**
     * 解密
     * @param bytes
     * @return
     */
    @Override
    public byte[] decrypt(byte[] bytes){
        bytes[0] = (byte)~bytes[0];
        return bytes;
    }
}
