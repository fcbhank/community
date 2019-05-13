package com.fcb.community.controller;

import com.fcb.community.dto.QuestionDto;
import com.fcb.community.mapper.UserMapper;
import com.fcb.community.model.User;
import com.fcb.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hank on 2019
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private Environment environment;

    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                }
                break;
            }

        // 获取数据库中已发布问题的list
        List<QuestionDto> questionDtos = questionService.list();
        model.addAttribute("questionDtos", questionDtos);
        // 设置登录跳转地址中的常量
        model.addAttribute("clientId", environment.getProperty("github.client.id"));
        model.addAttribute("redirectUri", environment.getProperty("github.redirect.uri"));
        model.addAttribute("scope", "user");
        model.addAttribute("state", 1);
        return "index";

    }
}