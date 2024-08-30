package com.example.demo.ports.application.http.mapper.category;

import com.example.demo.domain.model.Category;
import com.example.demo.ports.application.http.dto.CategoryResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse categoryToCategoryResponse(Category category);
    List<CategoryResponse> categoriesToCategoryResponses(List<Category> categories);
}
