package com.fcb.community.exception;

/**
 * Created by hank on 7/7/19
 */
public class CustomizeException extends RuntimeException {
    private Integer code;
    private String tip;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.tip = errorCode.getTip();
    }


    public Integer getCode() {
        return code;
    }

    public String getTip() {
        return tip;
    }
}
