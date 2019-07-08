package com.fcb.community.service;

import com.fcb.community.dto.PaginationDTO;
import com.fcb.community.dto.QuestionDTO;
import com.fcb.community.exception.CustomizeErrorCode;
import com.fcb.community.exception.CustomizeException;
import com.fcb.community.mapper.QuestionExtMapper;
import com.fcb.community.mapper.QuestionMapper;
import com.fcb.community.mapper.UserMapper;
import com.fcb.community.model.Question;
import com.fcb.community.model.QuestionExample;
import com.fcb.community.model.User;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer currentPage, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        // 按照size分的总共页数
        Integer totalPage;

        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
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

        paginationDTO.setPagination(totalPage, currentPage);
        //offset=size*(currentPage-1)
        Integer offset = size * (currentPage - 1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
        List<QuestionDTO> questionDTOs = new ArrayList<>();

        if (questions != null && questions.size() != 0)
            for (Question question : questions) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                // 将question对象复制到questionDTO对象中
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOs.add(questionDTO);
            }
        paginationDTO.setQuestionDTOs(questionDTOs);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer currentPage, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        // 按照size分的总共页数
        Integer totalPage;

        QuestionExample example = new QuestionExample();
        example.createCriteria().
                andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(example);
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


        paginationDTO.setPagination(totalPage, currentPage);

        //offset=size*(currentPage-1)
        Integer offset = size * (currentPage - 1);
        QuestionExample userIdExample = new QuestionExample();
        userIdExample.createCriteria().
                andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(userIdExample, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOs = new ArrayList<>();

        if (questions != null && questions.size() != 0)
            for (Question question : questions) {
                User user = userMapper.selectByPrimaryKey(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                // 将question对象复制到questionDTO对象中
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOs.add(questionDTO);
            }
        paginationDTO.setQuestionDTOs(questionDTOs);
        return paginationDTO;
    }

    public QuestionDTO findById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(userMapper.selectByPrimaryKey(question.getCreator()));
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() != null) {
            // 更新问题
            Question updateQuestion = questionMapper.selectByPrimaryKey(question.getId());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setGmtModified(System.currentTimeMillis());
            QuestionExample example = new QuestionExample();
            example.createCriteria().
                    andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if (updated != 1) {
                //更新失败了
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

        } else {
            // 插入问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }
    }

    public void incViewCount(Integer id) {
        // 增加阅读数
        Question updateQuestion = new Question();
        updateQuestion.setId(id);
        updateQuestion.setViewCount(1);
        questionExtMapper.incViewCount(updateQuestion);
    }
}
