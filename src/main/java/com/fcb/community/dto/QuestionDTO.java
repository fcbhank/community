package com.fcb.community.dto;

import com.fcb.community.model.User;
import lombok.Data;

/**
 * Created by hank on 19-5-8
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
