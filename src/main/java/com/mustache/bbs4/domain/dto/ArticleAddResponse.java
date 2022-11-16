package com.mustache.bbs4.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleAddResponse {
    private Long id;
    private String title;
    private String content;
}
