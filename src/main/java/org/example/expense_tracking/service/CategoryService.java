package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.CategoryDTO;
import org.example.expense_tracking.model.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories(Integer userId);

    CategoryResponse getCategoryById(Integer id, Integer userId);

    void deleteCategoryById(Integer id, Integer userId);

    CategoryResponse insertNewCategory(CategoryDTO categoryDTO);

    CategoryResponse updateCategoryById(Integer id, CategoryDTO categoryDTO, Integer userId);
}
