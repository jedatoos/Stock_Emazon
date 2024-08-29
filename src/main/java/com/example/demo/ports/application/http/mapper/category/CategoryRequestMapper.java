package com.example.demo.ports.application.http.mapper.category;

import com.example.demo.domain.model.Category;
import com.example.demo.ports.application.http.dto.CategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper {
    Category categoryRequestToCategory(CategoryRequest categoryRequest);
}
