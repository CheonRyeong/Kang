package com.example.first.controller;


import com.example.first.dto.ArticleForm;
import com.example.first.entity.Article;
import com.example.first.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class FirstController {

    @Autowired
    private final ArticleRepository articleRepository;


    public FirstController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/hi")
    public String hello(Model model)
     {
         model.addAttribute("username", "hello");
        return "greeting";
    }

    @GetMapping("/articles")
    public String listpage(Model model)
    {
        List<Article> articlesEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articlesEntityList);
        return "list";
    }

    @GetMapping("/articles/new")
    public String newpage(Model model)
    {
        model.addAttribute("username", "new");
        return "new";
    }

    @GetMapping("/articles/{id}")
    public String detailpage(@PathVariable Long id, Model model)
    {
        log.info("id =" + id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "detail";
    }

    @GetMapping("/articles/{id}/edit")
    public String editpage(Model model)
    {
        model.addAttribute("username", "new");
        return "edit";
    }

    @PostMapping("/articles/create")
    public String createpage(ArticleForm form)
    {
        System.out.println(form.toString());
        Article article = form.toEntity();
        System.out.println(article.toString());
        Article saved =articleRepository.save(article);
        System.out.println(saved.toString());
        return "redirect:/articles";
    }

    @PostMapping("/articles/{id}/edit")
    public String reeditpage(Model model)
    {
        model.addAttribute("username", "new");
        return "new";
    }


    @PostMapping("/articles/{id}/delete")
    public String deletepage(Model model)
    {
        model.addAttribute("username", "new");
        return "new";
    }

}

//•	기능별 메서드:
//o	GET /articles → 글 목록
//o	GET /articles/new → 작성 폼
//o	POST /articles/new → 글 저장 후 목록 리다이렉트
//o	GET /articles/{id} → 글 상세 조회
//o	GET /articles/{id}/edit → 수정 폼
//o	POST /articles/{id}/edit → 글 수정 후 상세 페이지 리다이렉트
//o	POST /articles/{id}/delete → 글 삭제 후 목록 리다이렉트