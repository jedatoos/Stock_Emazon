package com.example.demo.ports.persistence.mysql.adapter;


import com.example.demo.domain.model.Category;
import com.example.demo.domain.spi.category.ICategoryPersistencePort;
import com.example.demo.ports.persistence.mysql.mapper.CategoryEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public boolean categoryExistsByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).isPresent();
    }
}
