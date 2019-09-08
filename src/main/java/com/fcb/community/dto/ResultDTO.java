package com.fcb.community.dto;

import com.fcb.community.exception.CustomizeErrorCode;
import com.fcb.community.exception.CustomizeException;
import lombok.Data;

import java.util.List;

/**
 * Created by hank on 7/9/19
 */
@Data
public class ResultDTO<T> {
    private Integer code;
    private String tip;
    private T data;

    public static ResultDTO okof() {
        ResultDTO result = new ResultDTO();
        result.setCode(200);
        result.setTip("请求成功！");
        return result;
    }

    public static <T> ResultDTO okof(T data) {
        ResultDTO result = new ResultDTO();
        result.setCode(200);
        result.setTip("请求成功！");
        result.setData(data);
        return result;
    }

    public static ResultDTO errof(Integer code, String tip) {
        ResultDTO result = new ResultDTO();
        result.setCode(code);
        result.setTip(tip);
        return result;
    }


    public static ResultDTO errof(CustomizeErrorCode errorCode) {
        return errof(errorCode.getCode(), errorCode.getTip());
    }

    public static ResultDTO errof(CustomizeException exception) {
        return errof(exception.getCode(), exception.getTip());
    }
}
