//package com.carcompare.shiro.filter;
//
//import com.carcompare.base.ApiResult;
//import com.carcompare.utils.AppUtil;
//import com.carcompare.utils.CacheUtil;
//import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * JWT认证过滤器
// */
//@Component
//public class CaptchaValidationFilter extends FormAuthenticationFilter {
//
//    @Autowired
//    CacheUtil cacheUtil;
//
//    //原FormAuthenticationFilter的认证方法
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        //取出页面的验证码
//        //输入的验证和session中的验证进行对比
//        String captchaCode = request.getParameter("captcha");
//        Object value = cacheUtil.getCache(CacheUtil.APP_CACHE, captchaCode);
//
//        if(value == null){
//            AppUtil.ResponseApiResult(httpResponse, HttpServletResponse.SC_UNAUTHORIZED,
//                    new ApiResult(false, "验证码不正确33!").toJSONString());
//
//            //拒绝访问，不再校验账号和密码
//            return true;
//        }
//
//        return super.onAccessDenied(request, response);
//    }
//}
