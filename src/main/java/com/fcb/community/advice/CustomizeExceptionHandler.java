package com.fcb.community.advice;


import com.alibaba.fastjson.JSON;
import com.fcb.community.dto.ResultDTO;
import com.fcb.community.exception.CustomizeErrorCode;
import com.fcb.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hank on 7/7/19
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    //通用异常处理
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            // 请求的是json，返回json
            if (e instanceof CustomizeException) {
                ResultDTO resultDTO = new ResultDTO();
                resultDTO.setTip(((CustomizeException) e).getTip());
                resultDTO.setCode(((CustomizeException) e).getCode());
                response.setStatus(resultDTO.getCode());
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                try {
                    PrintWriter writer = response.getWriter();
                    writer.write(JSON.toJSONString(resultDTO));
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        } else {
            // 返回错误页面
            if (e instanceof CustomizeException) {
                model.addAttribute("tip", ((CustomizeException) e).getTip());
            } else {
                model.addAttribute("tip", CustomizeErrorCode.SYS_ERROR.getTip());
            }
            return new ModelAndView("error");
        }


    }
}