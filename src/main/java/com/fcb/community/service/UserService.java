package com.fcb.community.service;

import com.fcb.community.mapper.UserMapper;
import com.fcb.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hank on 7/6/19
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void insertOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if (dbUser != null) {
            //更新
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setBio(user.getBio());
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            userMapper.update(dbUser);
        } else {
            // 插入
            userMapper.insert(user);
        }
    }
}
