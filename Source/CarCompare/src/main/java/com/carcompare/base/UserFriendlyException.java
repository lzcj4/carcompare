package com.carcompare.base;

/**
 * 自定义用户友好异常类
 */
public class UserFriendlyException extends RuntimeException {

    private Exception orignalException;

    public  UserFriendlyException(String message){
        super(message);
    }

    public UserFriendlyException(String message, Exception orignalException){
        super(message);
        this.orignalException = orignalException;
    }

    public Exception getOrignalException() {
        return orignalException;
    }
}
