package com.escass.bbs.controllers;

import com.escass.bbs.entities.ArticleEntity;
import com.escass.bbs.entities.CommentEntity;
import com.escass.bbs.results.Result;
import com.escass.bbs.services.ArticleService;
import com.escass.bbs.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getIndex(@RequestParam(value = "articleIndex", required = false, defaultValue = "1") int articleIndex) {
        ModelAndView modelAndView = new ModelAndView();
        ArticleEntity article = this.articleService.selectArticleByIndex(articleIndex);
        CommentEntity[] comments = this.commentService.selectCommentsByIndex(articleIndex);
        modelAndView.addObject("article", article);
        modelAndView.addObject("comments", comments);
        modelAndView.setViewName("article/read");
        return modelAndView;
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String postWrite(CommentEntity comment) {
        JSONObject response = new JSONObject();
        Result result = this.commentService.addComment(comment);
        response.put(Result.NAME, result.nameToLower());
        return response.toString();
    }
}
