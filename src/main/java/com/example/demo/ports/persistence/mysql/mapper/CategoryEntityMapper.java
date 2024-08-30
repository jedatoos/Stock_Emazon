package com.example.demo.ports.persistence.mysql.mapper;


import com.example.demo.domain.model.Category;
import com.example.demo.ports.persistence.mysql.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);

}
