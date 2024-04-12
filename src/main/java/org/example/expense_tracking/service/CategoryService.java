package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
}
