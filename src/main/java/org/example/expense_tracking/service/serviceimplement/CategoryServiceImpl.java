package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.response.CategoryResponse;
import org.example.expense_tracking.repository.CategoryRepository;
import org.example.expense_tracking.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse getCategoryById(Integer categoryId) {
        return categoryRepository.getCategoryById(categoryId);
    }
}
