package com.carcompare.base;

import org.apache.shiro.ShiroException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger loggwr = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 捕捉自定义友好异常
    @ExceptionHandler(UserFriendlyException.class)
    public ApiResult handleUserFriendlyException(UserFriendlyException e){
        Exception ex = e.getOrignalException();
        if(ex != null){
            this.writeError(ex);
        }
        return new ApiResult(false, e.getMessage(), null);
    }

    // 捕捉UnauthorizedException
    @ExceptionHandler(UnauthorizedException.class)
    public ApiResult handle401() {
        return new ApiResult(false, "权限不足", null);
    }

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public ApiResult handle401(ShiroException e) {
        this.writeError(e);
        return new ApiResult(false, "权限不足", null);
    }

    // 数据库异常
    @ExceptionHandler(MyBatisSystemException.class)
    public ApiResult myBatisSystemException(MyBatisSystemException e) {
        this.writeError(e);
        return new ApiResult(false, "数据库异常", null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public ApiResult globalException(HttpServletRequest request, Throwable ex, Exception e) {
        this.writeError(e);
        return new ApiResult(false, "操作异常", null);
    }

    private  void  writeError(Exception ex){
        loggwr.error(ex.getMessage(), ex);
    }
}
