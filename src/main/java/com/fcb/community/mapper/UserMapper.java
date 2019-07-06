package com.fcb.community.mapper;

import com.fcb.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by hank on 19-5-6
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(account_id, name, token, gmt_create, gmt_modified, bio, avatar_url) values(#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{creator}")
    User findById(int creator);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name=#{name}, token=#{token}, gmt_modified=#{gmtModified}, bio=#{bio}, avatar_url=#{avatarUrl} where id=#{id}")
    void update(User user);
}
