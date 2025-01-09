package com.shop.controller;

import com.shop.service.NaverNewsCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/crawl")
public class CrawlerController {
    @Autowired
    private NaverNewsCrawlerService crawlerService;

    /**
     *
     * @param keyword
     * @param model
     * @return
     */

    @GetMapping("/news")
    public String crawlNews(@RequestParam(required = false) String keyword, Model model) {
        if (keyword == null || keyword.isEmpty()) {
            keyword = "골프존";
        }
        List<NaverNewsCrawlerService.NewsArticle> articles = crawlerService.crawlNews(keyword);
        model.addAttribute("articles", articles);
        return "crawl/news"; // Thymeleaf 템플릿 호출
    }
}
