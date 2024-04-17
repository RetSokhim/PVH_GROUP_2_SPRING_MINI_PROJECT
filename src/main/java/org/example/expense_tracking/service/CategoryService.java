package org.example.expense_tracking.service;

import org.example.expense_tracking.exception.SearchNotFoundException;
import org.example.expense_tracking.model.dto.request.CategoryRequestDTO;
import org.example.expense_tracking.model.dto.response.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryResponse> getAllCategories(UUID userId,Integer size,Integer offset);

    CategoryResponse getCategoryById(UUID categoryId, UUID userId) throws SearchNotFoundException;

    void deleteCategoryById(UUID categoryId, UUID userId) throws SearchNotFoundException;

    CategoryResponse insertNewCategory(CategoryRequestDTO categoryRequestDTO);

    CategoryResponse updateCategoryById(UUID categoryId, CategoryRequestDTO categoryRequestDTO, UUID userId) throws SearchNotFoundException;

    Integer getTotalCategories(UUID userId);
}
