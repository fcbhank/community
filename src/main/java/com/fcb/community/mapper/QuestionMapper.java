package com.fcb.community.mapper;

import com.fcb.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by hank on 19-5-7
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title, description, gmt_create, gmt_modified, creator, tag) values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    //分页查询
    @Select("select * from question limit #{size} offset #{offset}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    // 获取question表的总记录数
    @Select("select count(1) from question")
    Integer count();

    // 获取某一用户对应的所有问题
    @Select("select * from question where creator=#{userId} limit #{size} offset #{offset}")
    List<Question> listByUserId(@Param(value = "userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    //获取某一用户对应问题的总数
    @Select("select count(1) from question where creator=#{userId} ")
    Integer countUserByUserId(@Param(value = "userId") Integer userId);

    // 根据问题的id查找
    @Select("select * from question where id=#{id}")
    Question findById(@Param(value = "id") Integer id);

    // 更新问题
    @Update("update question set title=#{title}, description=#{description}, tag=#{tag}, gmt_modified=#{gmtModified} where id=#{id}")
    void update(Question question);
}
