package com.example.demo.ports.persistence.mysql.adapter;

import com.example.demo.domain.model.Article;
import com.example.demo.domain.spi.IArticlePersistencePort;
import com.example.demo.ports.persistence.mysql.mapper.IArticleEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ArticleAdapter implements IArticlePersistencePort {

    private  final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;
    @Override
    public void saveArticle(Article article) {
        articleRepository.save(articleEntityMapper.toEntity(article));

    }
}
