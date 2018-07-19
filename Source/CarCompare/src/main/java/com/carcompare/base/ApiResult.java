package com.carcompare.base;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * Api调用结果返回类
 */
@Data
public class ApiResult<T> {
    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误描述(success=false时会设置错误信息)
     */
    private String error;

    /**
     * 编码
     */
    private String code;

    /**
     * 返回数据
     */
    private T data;

    public ApiResult() {
        this(true, "", null);
    }

    public ApiResult(boolean success) {
        this(success, "", null);
    }

    public ApiResult(boolean success, T data) {
        this(success, "", data);
    }

    public ApiResult(boolean success, String error, T data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public ApiResult(boolean success, String code, String error, T data) {
        this.success = success;
        this.code = code;
        this.error = error;
        this.data = data;
    }

    public String toJSONString(){
       return JSONObject.toJSONString(this);
    }
}