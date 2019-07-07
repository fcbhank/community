package com.fcb.community.service;

import com.fcb.community.mapper.UserMapper;
import com.fcb.community.model.User;
import com.fcb.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hank on 7/6/19
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        if (users.size() != 0) {
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            updateUser.setBio(user.getBio());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setGmtModified(System.currentTimeMillis());

            UserExample updateExample = new UserExample();
            updateExample.createCriteria()
                    .andIdEqualTo(dbUser.getId());

            userMapper.updateByExampleSelective(updateUser, updateExample);
        } else {
            // 插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
