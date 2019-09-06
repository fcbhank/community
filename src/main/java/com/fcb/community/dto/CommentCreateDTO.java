package com.fcb.community.dto;

import lombok.Data;

/**
 * 在问题页面发布评论的 DTO
 * Created by hank on 7/8/19
 */

@Data
public class CommentCreateDTO {
    // 评论上一层Id
    private Long parentId;
    // 评论内容
    private String content;
    // 评论类型，针对问题的评论，针对评论的评论
    private Integer type;
}
