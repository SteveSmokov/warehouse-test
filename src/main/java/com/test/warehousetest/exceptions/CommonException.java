package com.test.warehousetest.exceptions;


public class CommonException extends Exception{
    private final Integer responseCode;

    public CommonException(String message, Integer responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public CommonException(String message, Throwable cause, Integer responseCode) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public CommonException(Throwable cause, Integer responseCode) {
        super(cause);
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public Integer getResponseCode() {
        return responseCode;
    }
}
