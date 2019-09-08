package com.fcb.community.controller;

import com.fcb.community.dto.CommentCreateDTO;
import com.fcb.community.dto.CommentDTO;
import com.fcb.community.dto.ResultDTO;
import com.fcb.community.enums.CommentTypeEnum;
import com.fcb.community.exception.CustomizeErrorCode;
import com.fcb.community.model.Comment;
import com.fcb.community.model.User;
import com.fcb.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hank on 7/8/19
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errof(CustomizeErrorCode.NO_LOGIN);
        }

        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errof(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setLikeCount(0L);
        comment.setCommentCount(0L);

        commentService.insert(comment);
        return ResultDTO.okof();
    }

    /**
     * 获取当前评论下的所有二级评论
     *
     * @param commentId
     * @return 返回一个包装了所有二级评论的 list 对象
     */
    @GetMapping("/comment/{commentId}")
    @ResponseBody
    public ResultDTO listSubCommentsByCommentId(@PathVariable(name = "commentId") Long commentId) {
        List<CommentDTO> subCommentDTOS = commentService.listCommentsByParentId(commentId, CommentTypeEnum.COMMENT);
        return ResultDTO.okof(subCommentDTOS);
    }
}
