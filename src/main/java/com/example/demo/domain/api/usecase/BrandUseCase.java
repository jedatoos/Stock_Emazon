package com.example.demo.domain.api.usecase;

import com.example.demo.domain.api.IBrandServicePort;
import com.example.demo.domain.exception.EntityAlreadyExistsException;
import com.example.demo.domain.model.Brand;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.spi.IBrandPersistencePort;
import com.example.demo.domain.util.PaginationUtil;

public class BrandUseCase implements IBrandServicePort {
    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort  brandPersistencePort){
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if (brandPersistencePort.brandExistsByName(brand.getBrandName())) {
            throw new EntityAlreadyExistsException("Brand");
        }
        brandPersistencePort.saveBrand(brand);
    }

    @Override
    public Pagination<Brand> getAllBrandsPaginated(PaginationUtil paginationBrandUtil) {
        return brandPersistencePort.getAllBrandsPaginated(paginationBrandUtil);
    }
}
