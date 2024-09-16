package com.example.demo.ports.application.http.controllers;

import com.example.demo.domain.api.IArticleServicePort;
import com.example.demo.domain.model.Article;
import com.example.demo.ports.application.http.dto.article.ArticleRequest;
import com.example.demo.ports.application.http.mappers.article.IArticleRequestMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@WebMvcTest(ArticleRestController.class)
@ContextConfiguration(classes = ArticleRestControllerTest.ArticleRestControllerSapientGeneratedTestConfig.class)
class ArticleRestControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private IArticleRequestMapper articleRequestMapperMock;

    @MockBean
    private IArticleServicePort articleServicePortMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach
    public void beforeTest() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleRestController(articleServicePortMock, articleRequestMapperMock)).build();
    }

    @AfterEach
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null) {
            autoCloseableMocks.close();
        }
    }

    @Test
    void saveArticleTest() throws Exception {
        // Arrange
        Article article = new Article();
        article.setArticleId(1L);
        article.setArticleName("Diadema");

        ArticleRequest articleRequest = new ArticleRequest(
                "Diadema",
                "Diadema con sonido envolvente",
                5,
                1000.0,
                11L,
                List.of(1L, 2L, 3L)
        );

        doReturn(article).when(articleRequestMapperMock).articleRequestToArticle(articleRequest);
        doNothing().when(articleServicePortMock).saveArticle(article);

        String contentStr = new ObjectMapper().writeValueAsString(articleRequest);

        ResultActions resultActions = this.mockMvc.perform(post("/articles")
                .content(contentStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
    }

    @SpringBootApplication(scanBasePackageClasses = ArticleRestController.class)
    static class ArticleRestControllerSapientGeneratedTestConfig {
    }
}