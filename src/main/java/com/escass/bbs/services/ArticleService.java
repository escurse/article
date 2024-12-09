package com.escass.bbs.services;

import com.escass.bbs.entities.ArticleEntity;
import com.escass.bbs.entities.CommentEntity;
import com.escass.bbs.mappers.ArticleMapper;
import com.escass.bbs.results.CommonResult;
import com.escass.bbs.vos.BoardVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleMapper articleMapper;

    public ArticleEntity selectArticleByIndex(int index) {
        if (index < 1) {
            return null;
        }
        return articleMapper.selectArticleByIndex(index);
    }

    public void increaseArticleView(ArticleEntity article) {
        if (article == null) {
            return;
        }
        article.setView(article.getView() + 1);
        this.articleMapper.updateArticle(article);
    }

    public Pair<ArticleEntity[], BoardVo> selectArticles(int page) {
        page = Math.max(1, page);
        int totalCount = this.articleMapper.selectAllArticlesCount();
        BoardVo boardVo = new BoardVo(page, totalCount);
        return Pair.of(this.articleMapper.selectArticles(boardVo.countPerPage, boardVo.offsetCount), boardVo);
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
