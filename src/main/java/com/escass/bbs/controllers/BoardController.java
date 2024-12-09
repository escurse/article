package com.escass.bbs.controllers;

import com.escass.bbs.entities.ArticleEntity;
import com.escass.bbs.services.ArticleService;
import com.escass.bbs.vos.BoardVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {
    private final ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getList(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Pair<ArticleEntity[], BoardVo> articles = this.articleService.selectArticles(page);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("articles", articles.getLeft());
        modelAndView.addObject("vos", articles.getRight());
        modelAndView.setViewName("board/list");
        return modelAndView;
    }
}
