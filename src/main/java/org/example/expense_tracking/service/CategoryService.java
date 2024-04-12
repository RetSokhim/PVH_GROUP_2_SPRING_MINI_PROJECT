package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.CategoryDTO;
import org.example.expense_tracking.model.dto.response.CategoryRespond;
import org.example.expense_tracking.model.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryRespond> getAllCategories(Integer page, Integer siz);

    Category getCategoryById(Integer id);

    Category insertNewCategory(CategoryDTO categoryDTO);

    Category deleteCategoryById(Integer id);

    Category updateCategoryById(Integer id, CategoryDTO categoryDTO);
}

