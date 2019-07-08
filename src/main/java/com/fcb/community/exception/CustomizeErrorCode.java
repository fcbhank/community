package com.fcb.community.exception;

/**
 * Created by hank on 7/7/19
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在了，要不换个试试？");

    private String errorTip;

    CustomizeErrorCode(String errorTip) {
        this.errorTip = errorTip;
    }

    @Override
    public String getErrorTip() {
        return errorTip;
    }
}
