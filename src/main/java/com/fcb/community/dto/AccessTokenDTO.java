package com.fcb.community.dto;

import lombok.Data;

/**
 * Created by hank on 19-5-4
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
