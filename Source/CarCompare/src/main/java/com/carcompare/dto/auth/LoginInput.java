package com.carcompare.dto.auth;

import org.springframework.mobile.device.Device;

public class LoginInput {
    private String username;
    private String password;
    private String captcha;
    private Device device;

    public LoginInput(String username, String password, String captcha, Device device){
        this.username = username;
        this.password = password;
        this.captcha = captcha;
        this.device = device;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public Device getDevice() {
        return device;
    }
}
