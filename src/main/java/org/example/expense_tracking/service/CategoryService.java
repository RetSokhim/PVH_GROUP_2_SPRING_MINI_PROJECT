package org.example.expense_tracking.service;

import org.example.expense_tracking.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories(Integer page, Integer size);

    Category getCategoryById(Integer categoryId);

    void deleteCategoryById(Integer categoryId);
}
