package com.mustache.bbs4.domain.dto;

import com.mustache.bbs4.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(id, title, content);
    }
}
