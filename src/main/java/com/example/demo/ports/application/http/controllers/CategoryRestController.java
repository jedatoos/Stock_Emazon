package com.example.demo.ports.application.http.controllers;


import com.example.demo.domain.api.ICategoryServicePort;
import com.example.demo.domain.model.Category;
import com.example.demo.domain.model.Pagination;
import com.example.demo.domain.util.PaginationUtil;
import com.example.demo.ports.application.http.dto.category.CategoryRequest;
import com.example.demo.ports.application.http.dto.category.CategoryResponse;
import com.example.demo.ports.application.http.mappers.category.CategoryRequestMapper;
import com.example.demo.ports.application.http.mappers.category.ICategoryResponseMapper;
import com.example.demo.ports.application.http.util.CategoryValidationConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Category API")
public class CategoryRestController {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Operation(summary = "Save a new category", description = "Creates a new category in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "409", description = "Category already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<String> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.categoryRequestToCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryValidationConstants.CATEGORY_SAVED_SUCCESS_MESSAGE);
    }
    @Operation(summary = "Get all categories paginated", description = "Retrieves a paginated list of categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categories retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Pagination<CategoryResponse>> getAllCategoriesPaginated(
            @Parameter(description = "Page number", example = "1")
            @RequestParam(defaultValue = "1", required = false) int page,
            @Parameter(description = "Page size", example = "10")
            @RequestParam(defaultValue = "1", required = false) int size,
            @Parameter(description = "Category name filter", example = "categoryName")
            @RequestParam(defaultValue = "categoryName",required = false) String nameFilter,
            @Parameter(description = "Sort order", example = "true")
            @RequestParam(defaultValue = "true",required = false) boolean isAscending
    ) {
        Pagination<Category> categoryPagination = categoryServicePort.getAllCategoriesPaginated(new PaginationUtil(size,page, nameFilter, isAscending));
        List<Category> categories = categoryPagination.getContent();

        return ResponseEntity.ok(
                new Pagination<>(
                        categoryPagination.isAscending(),
                        categoryPagination.getCurrentPage(),
                        categoryPagination.getTotalPages() ,
                        categoryPagination.getTotalElements(),
                        categoryResponseMapper.categoriesToCategoryResponses(categories)
                )
        );
    }




}
