package com.daishuai.jdbc.domain;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/2/14 16:59
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class RespResult {

    private String code;

    private String message;

    private Object object;

    public RespResult(String code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static RespResult error(Object data) {
        return new RespResult("1111", "失败", data);
    }

    public static RespResult success(Object data) {
        return new RespResult("0000", "成功", data);
    }
}
