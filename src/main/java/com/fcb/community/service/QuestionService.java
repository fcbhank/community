package com.fcb.community.service;

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


    public List<QuestionDto> list() {

        List<Question> questions = questionMapper.list();
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
        return questionDtos;
    }
}
