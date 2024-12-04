package com.escass.bbs.services;

import com.escass.bbs.entities.ArticleEntity;
import com.escass.bbs.mappers.ArticleMapper;
import com.escass.bbs.results.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleService {
    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleService(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    public ArticleEntity selectArticleByIndex(int index) {
        if (index < 1) {
            return null;
        }
        return articleMapper.selectArticleByIndex(index);
    }

    public CommonResult increaseArticleView(ArticleEntity article) {
        if (article == null) {
            return CommonResult.FAILURE;
        }
        article.setView(article.getView() + 1);
        return this.articleMapper.updateArticle(article) > 0
                ? CommonResult.SUCCESS
                : CommonResult.FAILURE;
    }

    public ArticleEntity[] selectArticles() {
        return this.articleMapper.selectArticles();
    }

    public CommonResult insertArticle(ArticleEntity article) {
        if (article == null ||
                article.getWriter() == null || article.getWriter().length() < 2 || article.getWriter().length() > 10 ||
                article.getTitle() == null || article.getTitle().isEmpty() || article.getTitle().length() > 100 ||
                article.getContent() == null || article.getContent().isEmpty() || article.getContent().length() > 10000) {
            return CommonResult.FAILURE;
        }
        article.setCreatedAt(LocalDateTime.now());
        article.setModifiedAt(null);
        article.setView(0);
        article.setIsDeleted(false);
        int affectedRows = this.articleMapper.insertArticle(article);
        return affectedRows > 0
                ? CommonResult.SUCCESS
                : CommonResult.FAILURE;
    }
}
