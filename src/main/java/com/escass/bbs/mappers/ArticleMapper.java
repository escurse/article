package com.escass.bbs.mappers;

import com.escass.bbs.entities.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {
    int insertArticle(ArticleEntity article);

    int updateArticle(ArticleEntity article);

    int selectAllArticlesCount();

    ArticleEntity[] selectArticles(@Param(value = "limitCount") int limitCount,
                                   @Param(value = "offsetCount") int offsetCount);

    ArticleEntity selectArticleByIndex(@Param(value = "index") int index);
}
