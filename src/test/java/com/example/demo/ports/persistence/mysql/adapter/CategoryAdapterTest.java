package com.example.demo.ports.persistence.mysql.adapter;

import com.example.demo.domain.model.Category;
import com.example.demo.ports.persistence.mysql.entity.CategoryEntity;
import com.example.demo.ports.persistence.mysql.mapper.CategoryEntityMapper;
import com.example.demo.ports.persistence.mysql.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
public class CategoryAdapterTest {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private CategoryEntityMapper categoryEntityMapper;

    @InjectMocks
    private CategoryAdapter categoryAdapter;

    private Category category;
    private CategoryEntity categoryEntity;

    @BeforeEach
    void setUp() {
        // Inicialización de la categoría y su entidad equivalente
        category = new Category(1L, "Test Category", "Description of test category");

        categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(1L);
        categoryEntity.setCategoryName("Test Category");
        categoryEntity.setCategoryDescription("Description of test category");
    }

    @Test
    void testSaveCategory() {
        // Arrange: Configuramos el comportamiento del mock del mapper
        when(categoryEntityMapper.toEntity(category)).thenReturn(categoryEntity);

        // Act: Llamamos al método que estamos probando
        categoryAdapter.saveCategory(category);

        // Assert: Verificamos que el repositorio guarde la entidad mapeada
        verify(categoryRepository).save(categoryEntity);
    }

    @Test
    void testCategoryExistsByName_ReturnsTrue() {
        // Arrange: Configuramos el mock del repositorio para retornar un Optional con la entidad
        when(categoryRepository.findByCategoryName("Test Category")).thenReturn(Optional.of(categoryEntity));

        // Act: Llamamos al método que estamos probando
        boolean exists = categoryAdapter.categoryExistsByName("Test Category");

        // Assert: Verificamos que el método retorne true y que el repositorio fue llamado correctamente
        verify(categoryRepository).findByCategoryName("Test Category");
        assertTrue(exists);
    }

    @Test
    void testCategoryExistsByName_ReturnsFalse() {
        // Arrange: Configuramos el mock del repositorio para retornar un Optional vacío
        when(categoryRepository.findByCategoryName("Test Category")).thenReturn(Optional.empty());

        // Act: Llamamos al método que estamos probando
        boolean exists = categoryAdapter.categoryExistsByName("Test Category");

        // Assert: Verificamos que el método retorne false y que el repositorio fue llamado correctamente
        verify(categoryRepository).findByCategoryName("Test Category");
        assertFalse(exists);
    }
}