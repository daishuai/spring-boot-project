package com.daishuai.multi.web.common;

import lombok.Data;

/**
 * @Description: java类作用描述
 * @Author: daishuai
 * @CreateDate: 2019/4/24 21:21
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
@Data
public class ResponseMessage {

    private String code;

    private String message;

    private Object data;

    public ResponseMessage(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseMessage ok() {
        return ok(null);
    }

    public static ResponseMessage ok(Object data) {
        return new ResponseMessage("200", "处理成功", data);
    }
}
