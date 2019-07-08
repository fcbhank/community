package com.fcb.community.controller;

import com.fcb.community.dto.PaginationDTO;
import com.fcb.community.mapper.UserMapper;
import com.fcb.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(Model model,
                        @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                        @RequestParam(value = "size", defaultValue = "5") Integer size) {

        // 获取数据库中已发布问题的list，并分页显示
        PaginationDTO paginationDTO = questionService.list(currentPage, size);
        model.addAttribute("pagination", paginationDTO);


        // 设置登录跳转地址中的常量
        model.addAttribute("clientId", environment.getProperty("github.client.id"));
        model.addAttribute("redirectUri", environment.getProperty("github.redirect.uri"));
        model.addAttribute("scope", "user");
        model.addAttribute("state", 1);
        return "index";

    }
}