package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.dto.ArticleAddRequest;
import com.mustache.bbs4.domain.dto.ArticleAddResponse;
import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.domain.dto.ArticleResponse;
import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {
    ArticleService articleService;
    public ArticleRestController(ArticleService articleService){
        this.articleService=articleService;
    }
    @GetMapping(value="/{id}")
    public ResponseEntity<ArticleDto> get(@PathVariable Long id){
        ArticleDto articleDto=articleService.getArticleById(id);
        return ResponseEntity
                .ok()
                .body(articleDto);
    }

    @PostMapping("")
    public ResponseEntity<ArticleAddResponse> addArticle(ArticleAddRequest dto){
        ArticleAddResponse response = articleService.add(dto);
        return ResponseEntity.ok().body(response);
    }
}
