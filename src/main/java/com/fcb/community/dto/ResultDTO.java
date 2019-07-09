package com.fcb.community.dto;

import com.fcb.community.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * Created by hank on 7/9/19
 */
@Data
public class ResultDTO {
    private Integer code;
    private String tip;


    public static Object okof() {
        ResultDTO result = new ResultDTO();
        result.setCode(200);
        result.setTip("请求成功！");
        return result;
    }

    public static Object error(Integer code, String tip) {
        ResultDTO result = new ResultDTO();
        result.setCode(code);
        result.setTip(tip);
        return result;
    }

    public static Object errof(CustomizeErrorCode customize) {
        ResultDTO result = new ResultDTO();
        result.setCode(customize.getCode());
        result.setTip(customize.getTip());
        return result;
    }
}
