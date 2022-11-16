package com.mustache.bbs4.domain.entity;

import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.domain.dto.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="article4")
public class Article {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }

    public static ArticleDto of(Article article){
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent());
    }
}
