package com.carcompare.Imageencryption;

import org.springframework.stereotype.Component;

/**
 * 图片加解密方案一
 */
@Component
public class ImageDEncrypter1 implements IImageDEncrypter {
    private final static int OFFSET = 1;

    @Override
    public byte[] encrypt(byte[] bytes) {
        byte[] dest = new byte[bytes.length + OFFSET];
        System.arraycopy(bytes, 0, dest, OFFSET, bytes.length);
        return dest;
    }

    @Override
    public byte[] decrypt(byte[] bytes) {
        byte[] dest = new byte[bytes.length - OFFSET];
        System.arraycopy(bytes, OFFSET, dest, 0, dest.length);
        return dest;
    }
}
