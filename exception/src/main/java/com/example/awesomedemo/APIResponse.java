package com.example.awesomedemo;

/**
 * @author zz
 */
public class APIResponse {
    private boolean success;

    private int code;

    private String resMsg;

    private Object object;


    public APIResponse(boolean success, int code, String resMsg, Object object) {
        this.success = success;
        this.code = code;
        this.resMsg = resMsg;
        this.object = object;
    }
}
