package com.fcb.community.controller;

import com.fcb.community.dto.CommentDTO;
import com.fcb.community.dto.QuestionDTO;
import com.fcb.community.enums.CommentTypeEnum;
import com.fcb.community.service.CommentService;
import com.fcb.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by hank on 7/6/19
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        QuestionDTO questionDTO = questionService.findById(id);
        // 增加阅读数
        questionService.incViewCount(id);
        model.addAttribute("question", questionDTO);

        // 读取所有回复问题的评论
        List<CommentDTO> commentDTOs = commentService.listCommentsByParentId(id, CommentTypeEnum.QUESTION);

        model.addAttribute("comments", commentDTOs);
        return "question";
    }
}
