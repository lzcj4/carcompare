package com.carcompare.shiro.jwt;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.AuthenticationToken;
import org.springframework.beans.factory.annotation.Qualifier;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String principal;

    private String token;
    @Override
    public String getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public String getToken() {
        return token;
    }
}