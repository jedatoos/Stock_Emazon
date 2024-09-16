package com.example.demo.domain.api.usecase;

import com.example.demo.domain.api.IArticleServicePort;
import com.example.demo.domain.model.Article;
import com.example.demo.domain.spi.IArticlePersistencePort;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
    }

    @Override
    public void saveArticle(Article article) {
        articlePersistencePort.saveArticle(article);

    }
}
