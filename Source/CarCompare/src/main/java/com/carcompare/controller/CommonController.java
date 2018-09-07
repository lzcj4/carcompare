package com.carcompare.controller;

import com.carcompare.base.ApiResult;
import com.carcompare.base.AppConsts;
import com.carcompare.license.LicenseValidationResult;
import com.carcompare.license.LicenseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/common")
public class CommonController {
    @Autowired
    LicenseValidator licenseValidator;

    @Value("${custom.captcha.enable}")
    Boolean captchaEnable;

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResult unauthorized() {
        return new ApiResult(false, "Unauthorized", null);
    }

    /**
     * 获取系统当前时间
     * @return
     */
    @GetMapping("/current-time")
    public ApiResult getCurrentTime(){
        return new ApiResult(true, new Date());
    }

    /**
     * 获取搜索开始时间和结束时间
     * @return
     */
    @GetMapping("/search-time-range")
    public ApiResult getSearchTimeRange(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        //搜索开始时间为：当前时间往前推7天,时间为:00:00:00
        long start = calendar.getTimeInMillis() - 3600 * 24 * 7 * 1000;

        //搜索结束时间为：当前天的23:59:59
        long end = calendar.getTimeInMillis() + 3600 * 24 * 1000 - 1000;

        Map<String, Date> map = new HashMap<>();
        map.put("start", new Date(start));
        map.put("end", new Date(end));

        return new ApiResult(true, map);
    }

    /**
     * 获取License信息及验证结果
     * @return
     */
    @GetMapping("/license-validation-result")
    public ApiResult getLicenseValidResult(){
        LicenseValidationResult licenseValidationResult = licenseValidator.getLicenseValidationResult();
        return new ApiResult(true, licenseValidationResult);
    }

    /**
     * 获取系统配置
     * @return
     */
    @GetMapping("/settings")
    public ApiResult getSystemSettings(){
        Map<String, Object> map = new HashMap<>();

        map.put("captchaEnable", captchaEnable);
        map.put("frontendAdministratorRoleCode", AppConsts.FRONTEND_ADMIN_ROLE_CODE);

        return new ApiResult(true, map);
    }
}
