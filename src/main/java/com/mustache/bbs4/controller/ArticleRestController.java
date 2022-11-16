package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.dto.ArticleResponse;
import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {
    ArticleService articleService;
    public ArticleRestController(ArticleService articleService){
        this.articleService=articleService;
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<ArticleResponse> get(@PathVariable Long id){
        ArticleResponse articleResponse=articleService.getArticle(id);
        return ResponseEntity
                .ok()
                .body(articleResponse);
    }
}
