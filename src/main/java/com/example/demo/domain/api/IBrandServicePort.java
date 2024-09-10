package com.example.demo.domain.api;

import com.example.demo.domain.model.Brand;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.util.PaginationUtil;


public interface IBrandServicePort {
    void saveBrand(Brand brand);
    Pagination<Brand> getAllBrandsPaginated(PaginationUtil paginationBrandUtil);

}
