package com.carcompare.interceptor;

import com.carcompare.base.ApiResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * 控制器参数验证拦截器
 */
@Aspect
@Component
public class ControllerValidatorInterceptor {

    /**
     * 拦截com.carcompare.controller包下面的方法，且方法最后一个参数是bindingResult
     * @param point
     * @param bindingResult
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.carcompare.controller.*.*(..)) && args(.., bindingResult)")
    public Object doAround(ProceedingJoinPoint point, BindingResult bindingResult) throws Throwable{
        Object returnValue;
        if(bindingResult.hasErrors()){
            returnValue = new ApiResult(false, buildErrors(bindingResult), null);
        }else {
            returnValue = point.proceed();
        }
        return returnValue;
    }

    private String buildErrors(BindingResult bindingResult){
        StringBuilder sb = new StringBuilder();
        sb.append("以下参数验证失败：<br/>");

        for(int i=0; i<bindingResult.getErrorCount(); i++){
            ObjectError error = bindingResult.getAllErrors().get(i);
            sb.append(String.format("<br/>%s.", i + 1));
            sb.append(error.getDefaultMessage());
        }

        return sb.toString();
    }

}
