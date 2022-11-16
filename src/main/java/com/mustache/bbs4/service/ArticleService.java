package com.mustache.bbs4.service;

import com.mustache.bbs4.domain.dto.ArticleAddRequest;
import com.mustache.bbs4.domain.dto.ArticleAddResponse;
import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.domain.dto.ArticleResponse;
import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto getArticleById(Long id){
        Optional<Article> optArticle=articleRepository.findById(id);
        ArticleDto article=Article.of(optArticle.get());

        return article;
    }
    public ArticleAddResponse add(ArticleAddRequest dto){
        Article article=dto.toEntity();
        Article savedArticle=articleRepository.save(article);
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent());
    }
}
