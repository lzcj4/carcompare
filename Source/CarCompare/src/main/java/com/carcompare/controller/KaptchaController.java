package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/captcha")
public class KaptchaController {
    @Autowired
    CaptchaService captchaService;

    @PostMapping("/image")
    public ApiResult captcha() throws Exception{
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        Map<String, Object> map = new HashMap<>();

        try {
            BufferedImage challenge = captchaService.createImage();
            ImageIO.write(challenge, "jpg", jpegOutputStream);

            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            map.put("img", encoder.encode(jpegOutputStream.toByteArray()));

            return new ApiResult(true, map);
        } catch (IllegalArgumentException e) {
            return new ApiResult(false);
        }
    }
}
