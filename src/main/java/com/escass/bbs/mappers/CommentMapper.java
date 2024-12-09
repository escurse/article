package com.escass.bbs.mappers;

import com.escass.bbs.entities.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
    int insertComment(CommentEntity comment);

    CommentEntity[] selectCommentsByArticleIndex(@Param(value = "articleIndex") int articleIndex);
}
