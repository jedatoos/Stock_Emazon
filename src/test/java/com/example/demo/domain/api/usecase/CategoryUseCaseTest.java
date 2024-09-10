package com.example.demo.domain.api.usecase;

import com.example.demo.domain.exception.EntityAlreadyExistsException;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.spi.ICategoryPersistencePort;

import com.example.demo.domain.util.PaginationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class CategoryUseCaseTest {

    private final ICategoryPersistencePort categoryPersistencePortMock = mock(ICategoryPersistencePort.class, "categoryPersistencePort");

    @Test
    void saveCategoryWhenCategoryPersistencePortCategoryExistsByNameCategoryGetCategoryNameThrowsEntityAlreadyExistsException() {
        // Arrange Statement
        doReturn(true).when(categoryPersistencePortMock).categoryExistsByName("Electronics");
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);
        Category category = new Category();
        category.setCategoryName("Electronics");

        // Act Statement
        EntityAlreadyExistsException result = assertThrows(EntityAlreadyExistsException.class, () -> target.saveCategory(category));

        // Assert statement(s)
        assertAll("result",
                () -> assertThat(result, is(notNullValue())),
                () -> verify(categoryPersistencePortMock).categoryExistsByName("Electronics")
        );
    }


    @Test()
    void saveCategoryWhenCategoryPersistencePortNotCategoryExistsByNameCategoryGetCategoryName() {

        //(categoryPersistencePort.categoryExistsByName(category.getCategoryName())) : false

        //Arrange Statement

        doReturn(false).when(categoryPersistencePortMock).categoryExistsByName("Electronics");
        Category category = new Category();
        category.setCategoryName("Electronics");
        doNothing().when(categoryPersistencePortMock).saveCategory(category);
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);

        //Act Statement
        target.saveCategory(category);

        //Assert statement
        assertAll("result", () -> {
            verify(categoryPersistencePortMock).categoryExistsByName("Electronics");
            verify(categoryPersistencePortMock).saveCategory(category);
        });
    }

    @Test()
    void getAllCategoriesPaginatedTest() {
        //Arrange Statement
        Pagination<Category> paginationMock = mock(Pagination.class);
        PaginationUtil paginationUtilMock = mock(PaginationUtil.class);
        doReturn(paginationMock).when(categoryPersistencePortMock).getAllCategoriesPaginated(paginationUtilMock);
        CategoryUseCase target = new CategoryUseCase(categoryPersistencePortMock);

        //Act Statement
        Pagination<Category> result = target.getAllCategoriesPaginated(paginationUtilMock);

        //Assert statement
        assertAll("result", () -> {
            assertThat(result, equalTo(paginationMock));
            verify(categoryPersistencePortMock).getAllCategoriesPaginated(paginationUtilMock);
        });
    }



}