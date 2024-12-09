package com.escass.bbs.services;

import com.escass.bbs.entities.CommentEntity;
import com.escass.bbs.mappers.CommentMapper;
import com.escass.bbs.results.CommonResult;
import com.escass.bbs.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;

    public CommentEntity[] selectCommentsByIndex(int articleIndex) {
        if (articleIndex < 1) {
            return null;
        }
        return this.commentMapper.selectCommentsByArticleIndex(articleIndex);
    }

    public Result addComment(CommentEntity comment) {
        if (comment == null ||
        comment.getWriter() == null || comment.getWriter().length() < 2 || comment.getWriter().length() > 10 ||
        comment.getContent() == null || comment.getContent().isEmpty() || comment.getContent().length() > 1000) {
            return CommonResult.FAILURE;
        }
        comment.setCreatedAt(LocalDateTime.now());
        comment.setModifiedAt(null);
        comment.setIsDeleted(false);
        return this.commentMapper.insertComment(comment) > 0
                ? CommonResult.SUCCESS
                : CommonResult.FAILURE;
    }
}
