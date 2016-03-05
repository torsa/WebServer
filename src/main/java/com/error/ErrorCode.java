package com.error;

/**
 * Created by dtorsa on 5/03/2016.
 */
public enum ErrorCode {
    BAD_REQUEST("400","Bad Requst"),
    NOT_FOUND("404","Not Found"),
    FORBIDDEN("403","Forbidden"),
    INTERNAL_SERVER_ERROR("500","Internal Server Error");

    private String errorName;
    private String code;

    private ErrorCode(String code,String name) {
        this.errorName = name;
        this.code =code;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getCode() {
        return code;
    }

}
