package com.example.demo.domain.spi.category;


import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.PageResult;
import com.example.demo.domain.util.PageResultUtil;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean categoryExistsByName(String categoryName);
    PageResult<Category> getAllCategoriesPaginated(PageResultUtil pageResultUtil);



}
