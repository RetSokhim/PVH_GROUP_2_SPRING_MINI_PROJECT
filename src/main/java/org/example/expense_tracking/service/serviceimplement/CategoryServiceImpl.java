package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.entity.Category;
import org.example.expense_tracking.repository.CategoryRepository;
import org.example.expense_tracking.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size) {
        return categoryRepository.getAllCategories(page, size);
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {

    }
}
