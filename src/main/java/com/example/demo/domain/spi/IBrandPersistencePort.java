package com.example.demo.domain.spi;

import com.example.demo.domain.model.Brand;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.util.PaginationUtil;


public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    boolean brandExistsByName(String brandName);
    Pagination<Brand> getAllBrandsPaginated(PaginationUtil paginationBrandUtil);
}
