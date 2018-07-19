package com.carcompare.filter;

import com.carcompare.core.authentication.AccountManager;
import com.carcompare.utils.AppUtil;
import com.carcompare.utils.SpringUtils;
import com.carcompare.utils.TokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*",filterName = "refreshTokenFilter",asyncSupported = true)
public class RefreshTokenFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = null;

        String token = AppUtil.getToken(httpRequest);
        if(StringUtils.isEmpty(token) || token == null){
            doNextFilter(filterChain, servletRequest, servletResponse);
            return;
        }

        TokenUtil tokenUtil = SpringUtils.getBean(TokenUtil.class);

        Claims claims = null;
        try{
            claims = tokenUtil.getClaims(token);
        }catch (Exception ex){
            doNextFilter(filterChain, servletRequest, servletResponse);
            return;
        }

        if(claims == null){
            doNextFilter(filterChain, servletRequest, servletResponse);
            return;
        }

        if(!tokenUtil.canRefresh(claims)){
            doNextFilter(filterChain, servletRequest, servletResponse);
            return;
        }

        httpResponse = (HttpServletResponse) servletResponse;
        String newToken = tokenUtil.refreshToken(claims);
        httpResponse.setHeader("newToken", newToken);

        refreshToken(claims, newToken);

        doNextFilter(filterChain, servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void doNextFilter(FilterChain filterChain, ServletRequest servletRequest, ServletResponse servletResponse)throws IOException, ServletException{
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void refreshToken(Claims claims, String newToken){
        Object userId = claims.get(TokenUtil.CLAIM_KEY_USERID);
        AccountManager accountManager = SpringUtils.getBean(AccountManager.class);
        accountManager.refreshToken(userId, newToken);
    }
}