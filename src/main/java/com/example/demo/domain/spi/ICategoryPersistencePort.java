package com.example.demo.domain.spi;


import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.util.PaginationUtil;

public interface ICategoryPersistencePort {
    void saveCategory(Category category);
    boolean categoryExistsByName(String categoryName);
    Pagination<Category> getAllCategoriesPaginated(PaginationUtil pageResultUtil);




}
