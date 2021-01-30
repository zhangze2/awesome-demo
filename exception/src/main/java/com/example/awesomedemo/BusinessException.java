package com.example.awesomedemo;

/**
 * @author zz
 */
public class BusinessException extends RuntimeException{


    private int errorCode;

    public BusinessException(String message, int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
