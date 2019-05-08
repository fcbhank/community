package com.fcb.community.dto;

import com.fcb.community.model.User;
import lombok.Data;

/**
 * Created by hank on 19-5-8
 */
@Data
public class QuestionDto {
    private int id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;
    private User user;
}
