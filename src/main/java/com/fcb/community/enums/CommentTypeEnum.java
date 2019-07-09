package com.fcb.community.enums;

/**
 * Created by hank on 7/9/19
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExit(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (type == value.type) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
