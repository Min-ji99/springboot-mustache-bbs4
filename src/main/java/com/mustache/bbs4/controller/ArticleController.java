package com.mustache.bbs4.controller;

import com.mustache.bbs4.domain.entity.Article;
import com.mustache.bbs4.domain.dto.ArticleDto;
import com.mustache.bbs4.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    ArticleRepository articleRepository;
    public ArticleController(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/articles/list";
    }
    @GetMapping("/new")
    public String createPage(){
        return "new";
    }
    @PostMapping("")
    public String articles(ArticleDto articleDto){
        log.info(articleDto.getTitle());
        Article savedArticle=articleRepository.save(articleDto.toEntity());
        return "redirect:/articles/"+savedArticle.getId();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "show";
        }
        return "error";
    }
    @GetMapping("/list")
    public String findAll(Model model){
        List<Article> list=articleRepository.findAll();
        model.addAttribute("articles", list);
        return "list";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Optional<Article> optArticle = articleRepository.findById(id);
        if (!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "edit";
        }
        return "error";
    }
    @PostMapping("/{id}/update")
    public String updatePost(@PathVariable Long id, ArticleDto articleDto){
        log.info("title : {} content : {}", articleDto.getTitle(), articleDto.getContent());
        Article article=articleRepository.save(articleDto.toEntity());
        return "redirect:/articles/"+article.getId();
    }
    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id){
        articleRepository.deleteById(id);
        return "redirect:/articles/list";
    }
}
