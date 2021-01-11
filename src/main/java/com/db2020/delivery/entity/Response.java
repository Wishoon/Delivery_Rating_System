package com.db2020.delivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private Boolean result;
    private String msg;
    private String errorCode;
    private String errorMsg;
    private Object data;

    public Response(Boolean result, String msg, String errorCode, String errorMsg) {
        this.result = result;
        this.msg = msg;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


}
