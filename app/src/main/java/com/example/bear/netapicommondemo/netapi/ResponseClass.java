package com.example.bear.netapicommondemo.netapi;

public class ResponseClass {
    private String code;

    public ResponseClass(String code, String errorCode) {
        this.code = code;
        this.errorCode = errorCode;
    }

    private String errorCode;

    public ResponseClass() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
