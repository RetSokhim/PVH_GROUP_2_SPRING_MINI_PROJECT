package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.response.CategoryResponse;

public interface CategoryService {
    CategoryResponse getCategoryById(Integer categoryId);
}
