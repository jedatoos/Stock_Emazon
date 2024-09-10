package com.example.demo.ports.persistence.mysql.adapter;

import com.example.demo.domain.model.Brand;

import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.spi.IBrandPersistencePort;
import com.example.demo.domain.util.PaginationUtil;
import com.example.demo.ports.persistence.mysql.entity.BrandEntity;
import com.example.demo.ports.persistence.mysql.mapper.IBrandEntityMapper;

import com.example.demo.ports.persistence.mysql.repository.IBrandRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class BrandAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public void saveBrand(Brand brand) {
        brandRepository.save(brandEntityMapper.toEntitybrand(brand));
    }

    @Override
    public boolean brandExistsByName(String brandName) {
        return brandRepository.findByBrandName(brandName).isPresent();

    }

    @Override
    public Pagination<Brand> getAllBrandsPaginated(PaginationUtil paginationBrandUtil) {
        // Determinar la dirección del ordenamiento (ascendente o descendente)
        Sort.Direction sortDirection = paginationBrandUtil.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;

// Crear un objeto PageRequest para la paginación y el ordenamiento
        PageRequest pageRequest = PageRequest.of(
                paginationBrandUtil.getPageNumber(),
                paginationBrandUtil.getPageSize(),
                Sort.by(sortDirection, paginationBrandUtil.getNameFilter())
        );

// Obtener la página de entidades desde el repositorio
        Page<BrandEntity> brandPage = brandRepository.findAll(pageRequest);

// Convertir las entidades a objetos de dominio Brand
        List<Brand> brands = brandEntityMapper.toBrandList(brandPage.getContent());

// Retornar un objeto BrandPagination que encapsula toda la información de la paginación
        return new Pagination<>(
                paginationBrandUtil.isAscending(),
                paginationBrandUtil.getPageNumber(),
                brandPage.getTotalPages(),
                brandPage.getTotalElements(),
                brands
        );
    }
}
