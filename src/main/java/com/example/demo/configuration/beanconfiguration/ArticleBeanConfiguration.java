package com.example.demo.configuration.beanconfiguration;

import com.example.demo.domain.api.IArticleServicePort;
import com.example.demo.domain.api.usecase.ArticleUseCase;
import com.example.demo.domain.spi.IArticlePersistencePort;
import com.example.demo.ports.persistence.mysql.adapter.ArticleAdapter;
import com.example.demo.ports.persistence.mysql.mapper.IArticleEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.IArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ArticleBeanConfiguration {

    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;

    @Bean
    public IArticlePersistencePort articlePersistencePort() {
        return new ArticleAdapter(articleRepository, articleEntityMapper);
    }

    @Bean
    IArticleServicePort articleServicePort() {
        return new ArticleUseCase(articlePersistencePort());
    }
}
