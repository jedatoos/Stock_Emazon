package com.example.demo.domain.spi;

import com.example.demo.domain.model.Article;

public interface IArticlePersistencePort {
    void  saveArticle(Article article);
}
