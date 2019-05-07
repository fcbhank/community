package com.fcb.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommunityApplication {

    public static void main(String[] args) {
        //可以完全关闭重启支持
        //System.setProperty("spring.devtools.restart.enabled", "false");

        SpringApplication.run(CommunityApplication.class, args);
    }

}
