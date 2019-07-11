package com.fcb.community.controller;

import com.fcb.community.dto.QuestionDTO;
import com.fcb.community.model.User;
import com.fcb.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hank on 7/6/19
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model,
                           HttpServletRequest request) {
        QuestionDTO questionDTO = questionService.findById(id);
        questionService.incViewCount(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
