package com.example.demo.ports.persistence.mysql.mapper;


import com.example.demo.domain.model.Category;
import com.example.demo.ports.persistence.mysql.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {
    CategoryEntity toEntity(Category category);
}
