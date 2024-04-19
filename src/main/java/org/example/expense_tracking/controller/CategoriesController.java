package org.example.expense_tracking.controller;

import jakarta.validation.constraints.Positive;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.model.entity.Category;
import org.example.expense_tracking.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {
    private final CategoryService categoryService;
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategories(
            @Positive
            @RequestParam(defaultValue = "1") Integer page,
            @Positive
            @RequestParam(defaultValue = "5") Integer size
    ) {
        ApiResponse<List<Category>> response = ApiResponse.<List<Category>>builder()
                .message("All Categories have been successfully founded")
                .payload(categoryService.getAllCategories(page,size))
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .code(201)
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@Positive @PathVariable("id") Integer categoryId) {
        if(categoryService.getCategoryById(categoryId) == null ) {
            return ResponseEntity.notFound().build();
        } else {
            ApiResponse<Category> response = ApiResponse.<Category>builder()
                    .message("The Category has been successfully founded.")
                    .payload(categoryService.getCategoryById(categoryId))
                    .status(HttpStatus.OK)
                    .time(LocalDateTime.now())
                    .code(201)
                    .build();
            return ResponseEntity.ok(response);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteCategoryById(@Positive @PathVariable("id") Integer categoryId) {
        categoryService.deleteCategoryById(categoryId);
        ApiResponse<Category> response = ApiResponse.<Category>builder()
                .message("The Category has been successfully removed")
                .status(HttpStatus.OK)
                .time(LocalDateTime.now())
                .code(201)
                .build();
        return ResponseEntity.ok(response);
    }
}
