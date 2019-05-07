package com.fcb.community.model;

import lombok.Data;

/**
 * Created by hank on 19-5-7
 */
@Data
public class Question {
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

}
