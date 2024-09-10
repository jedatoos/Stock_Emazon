package com.example.demo.ports.persistence.mysql.adapter;


import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.spi.ICategoryPersistencePort;
import com.example.demo.domain.util.PaginationUtil;
import com.example.demo.ports.persistence.mysql.entity.CategoryEntity;
import com.example.demo.ports.persistence.mysql.mapper.ICategoryEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public boolean categoryExistsByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName).isPresent();
    }

    @Override
    public Pagination<Category> getAllCategoriesPaginated(PaginationUtil pageResultUtil) {
        // Determinar la dirección del ordenamiento (ascendente o descendente)
        Sort.Direction sortDirection = pageResultUtil.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;

        // Crear un objeto PageRequest para la paginación y el ordenamiento
        PageRequest pageRequest = PageRequest.of(
                pageResultUtil.getPageNumber(),
                pageResultUtil.getPageSize(),
                Sort.by(sortDirection, pageResultUtil.getNameFilter())
        );

        // Obtener la página de entidades desde el repositorio
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageRequest);

        // Convertir las entidades a objetos de dominio Category
        List<Category> categories = categoryEntityMapper.toCategoryList(categoryPage.getContent());

        // Retornar un objeto PageResult que encapsula toda la información de la paginación
        return new Pagination<>(
                pageResultUtil.isAscending(),
                pageResultUtil.getPageNumber(),
                categoryPage.getTotalPages(),
                categoryPage.getTotalElements(),
                categories
        );
    }


}
