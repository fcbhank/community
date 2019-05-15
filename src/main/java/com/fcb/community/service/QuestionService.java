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
        Integer totalCount = questionMapper.count();
        paginationDto.setPagination(totalCount, currentPage, size);
        // 对currentPage做范围约束
        if (currentPage < 1 || currentPage > paginationDto.getTotalPage()) {
            currentPage = paginationDto.getCurrentPage();
        }

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
}
