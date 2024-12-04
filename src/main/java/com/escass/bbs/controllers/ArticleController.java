package com.escass.bbs.controllers;

import com.escass.bbs.entities.ArticleEntity;
import com.escass.bbs.results.Result;
import com.escass.bbs.services.ArticleService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getWrite() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("article/write");
        return modelAndView;
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String postWrite(ArticleEntity article, RedirectAttributes redirectAttributes, ModelAndView modelAndView) {
        JSONObject response = new JSONObject();
        Result result = this.articleService.insertArticle(article);
//        response.put(Result.NAME, result.nameToLower());
//        response.put(Result.RESULT, article.getIndex());
        if (result.nameToLower().equals("success")) {
            redirectAttributes.addAttribute("index", article.getIndex());
            return "redirect:/article/read";
        } else {
            return "redirect:/article/write";
        }
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView getRead(@RequestParam(value = "index", required = false, defaultValue = "0") int index) {
        ArticleEntity article = this.articleService.selectArticleByIndex(index);
        ModelAndView modelAndView = new ModelAndView();
        if (article != null) {
            this.articleService.increaseArticleView(article);
            modelAndView.addObject("article", article);
        }
        modelAndView.addObject("index", index);
        modelAndView.setViewName("article/read");
        return modelAndView;
    }
}
