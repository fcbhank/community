package com.fcb.community.controller;

import com.fcb.community.dto.CommentDTO;
import com.fcb.community.mapper.CommentMapper;
import com.fcb.community.model.Comment;
import com.fcb.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hank on 7/8/19
 */
@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setLikeCount(0L);

        User user = (User) request.getSession().getAttribute("user");
        comment.setCommentator(user.getId());

        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        commentMapper.insert(comment);
        return null;
    }
}
