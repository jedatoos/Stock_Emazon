package com.example.demo.domain.api.usecase;


import com.example.demo.domain.api.ICategoryServicePort;
import com.example.demo.domain.exception.EntityAlreadyExistsException;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.spi.category.ICategoryPersistencePort;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoriaPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoriaPersistencePort){
        this.categoriaPersistencePort = categoriaPersistencePort;
    }

    @Override
    public void saveCategory(Category category) {
        if (categoriaPersistencePort.categoryExistsByName(category.getCategoryName())) {
            throw new EntityAlreadyExistsException("Category");
        }
        categoriaPersistencePort.saveCategory(category);
    }
}
