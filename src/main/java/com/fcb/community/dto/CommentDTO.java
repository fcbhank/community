package com.fcb.community.dto;

import lombok.Data;

/**
 * Created by hank on 7/8/19
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
