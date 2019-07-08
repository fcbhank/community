package com.fcb.community.advice;


import com.fcb.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hank on 7/7/19
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    //通用异常处理
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if (e instanceof CustomizeException) {
            model.addAttribute("errorTip", ((CustomizeException) e).getErrorTip());
        } else {
            model.addAttribute("errorTip", "服务器跑丢了，请稍后再试～");
        }
        return new ModelAndView("error");
    }


}