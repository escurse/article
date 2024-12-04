package com.escass.bbs.controllers;

import com.escass.bbs.entities.ArticleEntity;
import com.escass.bbs.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {
    private final ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getList() {
        ArticleEntity[] articles = this.articleService.selectArticles();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("articles", articles);
        modelAndView.setViewName("board/list");
        return modelAndView;
    }
}
