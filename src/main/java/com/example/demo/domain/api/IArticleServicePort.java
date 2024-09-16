package com.example.demo.domain.api;

import com.example.demo.domain.model.Article;

public interface IArticleServicePort {
    void saveArticle(Article article);
}
