package com.fcb.community.dto;

import com.fcb.community.model.User;
import lombok.Data;

/**
 * 进入问题页面，返回到页面的 DTO
 * Created by hank on 9/6/19
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private String content;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private User user;

}
