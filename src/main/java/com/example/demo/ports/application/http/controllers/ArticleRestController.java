package com.example.demo.ports.application.http.controllers;

import com.example.demo.domain.api.IArticleServicePort;
import com.example.demo.domain.model.Article;
import com.example.demo.ports.application.http.dto.article.ArticleRequest;
import com.example.demo.ports.application.http.mappers.article.IArticleRequestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleRestController {
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;

    @Operation(summary = "Save a new article", description = "Creates a new article in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public void saveArticle(
            @Parameter(description = "Article request body", required = true)
            @Valid
            @RequestBody ArticleRequest articleRequest
    ) {
        Article article = articleRequestMapper.articleRequestToArticle(articleRequest);
        articleServicePort.saveArticle(article);
    }
}
