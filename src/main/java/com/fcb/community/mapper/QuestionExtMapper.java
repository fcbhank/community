package com.fcb.community.mapper;

import com.fcb.community.dto.QuestionDTO;
import com.fcb.community.model.Comment;
import com.fcb.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incViewCount(Question question);

    int incCommentCount(Question question);

    List<QuestionDTO> listRelatedQuestionByTag(Question question);
}