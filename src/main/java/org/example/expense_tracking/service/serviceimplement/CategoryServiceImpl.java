package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.response.CategoryResponse;
import org.example.expense_tracking.model.entity.Category;
import org.example.expense_tracking.repository.CategoryRepository;
import org.example.expense_tracking.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryResponse> getAllCategories(Integer userId) {
        List<Category> categories = categoryRepository.getAllCategories(userId);
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }

}
