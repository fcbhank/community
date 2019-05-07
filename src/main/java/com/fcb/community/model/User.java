package com.fcb.community.model;

import lombok.Data;

/**
 * Created by hank on 19-5-6
 */
@Data
public class User {
    private int id;
    private String accountId;
    private String name;
    private String token;
    private long gmtCreate;
    private long gmtModified;
    private String bio;
    private String avatarUrl;

}
