package com.example.demo.domain.api.usecase;

import com.example.demo.domain.exception.EntityAlreadyExistsException;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.spi.category.ICategoryPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryUseCaseTest {
    @Mock
    private  ICategoryPersistencePort categoriaPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;
    @Test
    @DisplayName("verifica que se lanza una excepción cuando se intenta guardar una categoría que ya existe")
    void saveCategory_WhenCategoryExists_ShouldThrowEntityAlreadyExistsException() {
        // Arrange
        Category category = new Category(1L, "Sports", "Category for sports items");

        when(categoriaPersistencePort.categoryExistsByName(category.getCategoryName())).thenReturn(true);

        // Act & Assert
        assertThrows(EntityAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));


    }
    @DisplayName("verifica que la categoría se guarda correctamente cuando no existe.")
    @Test
    void saveCategory_WhenCategoryDoesNotExist_ShouldSaveCategory() {
        // Arrange
        Category category = new Category(1L, "Sports", "Category for sports items");

        when(categoriaPersistencePort.categoryExistsByName(category.getCategoryName())).thenReturn(false);

        // Act
        categoryUseCase.saveCategory(category);

        // Assert
        verify(categoriaPersistencePort).saveCategory(category);
    }



}