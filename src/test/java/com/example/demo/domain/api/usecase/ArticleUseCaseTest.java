package com.example.demo.domain.api.usecase;

import com.example.demo.domain.model.Article;
import com.example.demo.domain.spi.IArticlePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ArticleUseCaseTest {

    private final IArticlePersistencePort articlePersistencePortMock = mock(IArticlePersistencePort.class, "articlePersistencePort");

    @Test
    void saveArticleTest() {
        // Arrange Statement
        Article articleMock = mock(Article.class);
        doNothing().when(articlePersistencePortMock).saveArticle(articleMock);
        ArticleUseCase target = new ArticleUseCase(articlePersistencePortMock);

        // Act Statement
        target.saveArticle(articleMock);

        // Assert statement
        assertAll("result", () -> verify(articlePersistencePortMock).saveArticle(articleMock));
    }
}