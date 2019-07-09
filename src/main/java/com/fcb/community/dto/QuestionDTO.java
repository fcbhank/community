package com.fcb.community.dto;

import com.fcb.community.model.User;
import lombok.Data;

/**
 * Created by hank on 19-5-8
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private long gmtCreate;
    private long gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private String description;
    private User user;
}
