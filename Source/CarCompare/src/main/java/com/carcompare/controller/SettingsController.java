package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/api")
public class SettingsController {

    @Value("${custom.captcha.enable}")
    Boolean captchaEnable;

    /**
     * 获取系统配置
     * @return
     */
    @GetMapping("/settings")
    public ApiResult getSystemSettings(){
        Map<String, Object> map = new HashMap<>();

        map.put("captchaEnable", captchaEnable);

        return new ApiResult(true, map);
    }
}
