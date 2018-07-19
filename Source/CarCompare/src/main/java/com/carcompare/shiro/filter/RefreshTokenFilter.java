package com.carcompare.shiro.filter;

import com.carcompare.utils.AppUtil;
import com.carcompare.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshTokenFilter extends AccessControlFilter {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = null;

        String token = AppUtil.getToken(httpRequest);
        if(StringUtils.isEmpty(token)){
            return true;
        }

        Claims claims = tokenUtil.getClaims(token);
        if(claims == null){
            return true;
        }

        if(!tokenUtil.needRefresh(claims)){
            return true;
        }

        httpResponse = (HttpServletResponse) servletResponse;
        String newToken = tokenUtil.refreshToken(claims);
        httpResponse.setHeader("newToken", newToken);

        return true;
    }
}
