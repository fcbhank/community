package com.fcb.community.mapper;

import com.fcb.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}