package com.example.first.dto;

import com.example.first.entity.Article;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    private String author;

    public ArticleForm() {}

    public ArticleForm(Long id,String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.id = id;
    }

    public ArticleForm(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.id = null;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(id,title,content,author,null);
    }
}
