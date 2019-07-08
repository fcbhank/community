package com.fcb.community.exception;

/**
 * Created by hank on 7/7/19
 */
public class CustomizeException extends RuntimeException {
    private String errorTip;

    public CustomizeException(ICustomizeErrorCode code) {
        this.errorTip = code.getErrorTip();
    }

    public String getErrorTip() {
        return errorTip;
    }
}
