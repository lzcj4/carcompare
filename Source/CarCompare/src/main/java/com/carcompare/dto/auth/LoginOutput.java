package com.carcompare.dto.auth;

import com.carcompare.core.authentication.LoginMode;
import lombok.Data;

@Data
public class LoginOutput {
    private String token;
    private String name;
    private String username;
    private Boolean isBackend;
    private String roleCode;
    private String defaultUrl;
    private LoginMode loginMode;
}