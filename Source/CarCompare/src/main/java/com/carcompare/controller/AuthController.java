package com.carcompare.controller;

import com.carcompare.core.authentication.LoginMode;
import com.carcompare.base.ApiResult;
import com.carcompare.dto.auth.LoginInput;
import com.carcompare.dto.auth.LoginOutput;
import com.carcompare.service.AuthService;
import com.carcompare.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AuthController {
    static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/login/frontend")
    public ApiResult loginToFrontend(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("captcha") String captcha, Device device) {
        LoginOutput output = this.authService.login(new LoginInput(username, password, captcha, device), LoginMode.FRONTEND);
        return new ApiResult(true, output);
    }

    @PostMapping("/login/backend")
    public ApiResult loginToBackend(@RequestParam("username") String username, @RequestParam("password") String password, Device device) {
        LoginOutput output = this.authService.login(new LoginInput(username, password, null, device), LoginMode.BACKEND);
        return new ApiResult(true, output);
    }

    /**
     * 登出
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/logout")
    public ApiResult logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.authService.logout();
        return new ApiResult(true);
    }
}
