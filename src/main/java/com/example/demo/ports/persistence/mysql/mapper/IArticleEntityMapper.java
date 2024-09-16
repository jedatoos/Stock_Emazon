package com.example.demo.ports.persistence.mysql.mapper;


import com.example.demo.domain.model.Article;
import com.example.demo.ports.persistence.mysql.entity.ArticleEntity;
import com.example.demo.ports.persistence.mysql.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {IBrandEntityMapper.class})


public interface IArticleEntityMapper {

    @Mapping(source = "brandId", target = "brand", qualifiedByName = "idToBrand")
    @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "idToCategory")
    ArticleEntity toEntity(Article article);


    @Named("idToCategory")
    default CategoryEntity idToCategory(Long categoryId) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(categoryId);
        return categoryEntity;
    }


}
