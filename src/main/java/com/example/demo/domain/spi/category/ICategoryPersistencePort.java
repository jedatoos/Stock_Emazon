package com.example.demo.domain.spi.category;


import com.example.demo.domain.model.Category;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean categoryExistsByName(String categoryName);



}
