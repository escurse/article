package com.escass.bbs.mappers;

import com.escass.bbs.entities.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    int insertArticle(ArticleEntity article);
    int updateArticle(ArticleEntity article);

    ArticleEntity[] selectArticles();

    ArticleEntity selectArticleByIndex(@Param(value="index") int index);
}
