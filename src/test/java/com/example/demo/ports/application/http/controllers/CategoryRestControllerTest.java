package com.example.demo.ports.application.http.controllers;

import com.example.demo.domain.api.ICategoryServicePort;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.util.PaginationUtil;
import com.example.demo.ports.application.http.dto.category.CategoryRequest;
import com.example.demo.ports.application.http.dto.category.CategoryResponse;
import com.example.demo.ports.application.http.mappers.category.CategoryRequestMapper;
import com.example.demo.ports.application.http.mappers.category.ICategoryResponseMapper;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
@WebMvcTest()
@ContextConfiguration(classes = CategoryRestController.class)
class CategoryRestControllerTest {
    private MockMvc mockMvc;

    @MockBean(name = "categoryRequestMapper")
    private CategoryRequestMapper categoryRequestMapperMock;

    @MockBean(name = "categoryResponseMapper")
    private ICategoryResponseMapper categoryResponseMapperMock;


    @MockBean(name = "categoryServicePort")
    private ICategoryServicePort categoryServicePortMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach()
    public void beforeTest() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new CategoryRestController(categoryServicePortMock, categoryRequestMapperMock, categoryResponseMapperMock)).build();

    }

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    @Test
    void saveCategoryTest() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("Electronics", "All electronic items");

        String contentStr = new ObjectMapper().writeValueAsString(categoryRequest);

        Category category = new Category();
        doReturn(category).when(categoryRequestMapperMock).categoryRequestToCategory(categoryRequest);
        doNothing().when(categoryServicePortMock).saveCategory(category);

        ResultActions resultActions = this.mockMvc.perform(post("/categories")
                .content(contentStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated());
    }

    @Test
    void getAllCategoriesPaginatedTest() throws Exception {
        List<Category> categoryList = new ArrayList<>();
        Pagination<Category> pagination = new Pagination<>(false, 0, 0, 0L, categoryList);
        doReturn(pagination).when(categoryServicePortMock).getAllCategoriesPaginated(any(PaginationUtil.class));
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        doReturn(categoryResponseList).when(categoryResponseMapperMock).categoriesToCategoryResponses(categoryList);

        ResultActions resultActions = this.mockMvc.perform(get("/categories")
                .param("size", String.valueOf(0))
                .param("page", String.valueOf(0))
                .param("nameFilter", "nameFilter1")
                .param("isAscending", String.valueOf(false))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages", is(0)))
                .andExpect(jsonPath("$.ascending", is(false)))
                .andExpect(jsonPath("$.currentPage", is(0)))
                .andExpect(jsonPath("$.content", hasSize(0)))
                .andExpect(jsonPath("$.totalElements", is(0)));
    }

    @SpringBootApplication(scanBasePackageClasses = CategoryRestController.class)
    static class CategoryRestControllerSapientGeneratedTestConfig {
    }
}
