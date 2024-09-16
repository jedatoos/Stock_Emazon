package com.example.demo.ports.application.http.mappers.article;

import com.example.demo.domain.model.Article;
import com.example.demo.ports.application.http.dto.article.ArticleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface IArticleRequestMapper {
    Article articleRequestToArticle(ArticleRequest articleRequest);

}
