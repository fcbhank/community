package com.fcb.community.service;

import com.fcb.community.dto.PaginationDto;
import com.fcb.community.dto.QuestionDto;
import com.fcb.community.mapper.QuestionMapper;
import com.fcb.community.mapper.UserMapper;
import com.fcb.community.model.Question;
import com.fcb.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hank on 19-5-8
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public PaginationDto list(Integer currentPage, Integer size) {

        PaginationDto paginationDto = new PaginationDto();

        // 按照size分的总共页数
        Integer totalPage;

        Integer totalCount = questionMapper.count();
        // 确定总共页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        // 对currentPage做范围约束
        if (currentPage < 1)
            currentPage = 1;

        if (currentPage > totalPage)
            currentPage = totalPage;

        paginationDto.setPagination(totalPage, currentPage);
        //offset=size*(currentPage-1)
        Integer offset = size * (currentPage - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDto> questionDtos = new ArrayList<>();

        if (questions != null && questions.size() != 0)
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDto questionDto = new QuestionDto();
                // 将question对象复制到questionDto对象中
                BeanUtils.copyProperties(question, questionDto);
                questionDto.setUser(user);
                questionDtos.add(questionDto);
            }
        paginationDto.setQuestionDtos(questionDtos);
        return paginationDto;
    }

    public PaginationDto list(Integer userId, Integer currentPage, Integer size) {
        PaginationDto paginationDto = new PaginationDto();

        // 按照size分的总共页数
        Integer totalPage;

        Integer totalCount = questionMapper.countUserByUserId(userId);
        // 确定总共页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        // 对currentPage做范围约束
        if (currentPage < 1)
            currentPage = 1;

        if (currentPage > totalPage)
            currentPage = totalPage;


        paginationDto.setPagination(totalPage, currentPage);

        //offset=size*(currentPage-1)
        Integer offset = size * (currentPage - 1);
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDto> questionDtos = new ArrayList<>();

        if (questions != null && questions.size() != 0)
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDto questionDto = new QuestionDto();
                // 将question对象复制到questionDto对象中
                BeanUtils.copyProperties(question, questionDto);
                questionDto.setUser(user);
                questionDtos.add(questionDto);
            }
        paginationDto.setQuestionDtos(questionDtos);
        return paginationDto;
    }

    public QuestionDto findById(Integer id) {
        Question question = questionMapper.findById(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);
        questionDto.setUser(userMapper.findById(question.getCreator()));
        return questionDto;
    }
}
