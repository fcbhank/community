package com.fcb.community.mapper;

import com.fcb.community.model.Question;

public interface QuestionExtMapper {
    int incViewCount(Question question);
}