package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.dto.ArticleResponse;
import com.mustache.bbs4.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Test
    @DisplayName("1개의 Json 형태로 잘 나오는지")
    void JsonResponse() throws Exception{
        ArticleResponse articleResponse=ArticleResponse.builder()
                .id(1)
                .title("룰루")
                .content("랄라")
                .build();

        given(articleService.getArticle(1))
                .willReturn(articleResponse);

        int articleId=1;

        String url=String.format("/api/v1/articles/%d", articleId);
        mockMvc.perform(get(url))
                .andExpect(status().idOk())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.title").value("룰루"))
                .andDo(print());
        verify(articleService).getArticle(articleId);
    }
}