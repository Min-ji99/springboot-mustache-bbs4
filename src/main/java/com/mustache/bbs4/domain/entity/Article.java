package com.mustache.bbs4.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
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
}
