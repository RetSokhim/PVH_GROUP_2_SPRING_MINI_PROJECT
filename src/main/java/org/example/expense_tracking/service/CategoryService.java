package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.response.CategoryRespond;
import org.example.expense_tracking.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryRespond> getAllCategories();

    CategoryRespond getCategoryById(Integer id);
}

