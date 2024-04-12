package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.request.CategoryDTO;
import org.example.expense_tracking.model.dto.response.CategoryRespond;
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
    public List<CategoryRespond> getAllCategories(Integer page, Integer siz) {
        List<Category> categories = categoryRepository.getAllCategories();
        List<CategoryRespond> categoryResponds = new ArrayList<>();
        for (Category category : categories) {
            CategoryRespond categoryRespond = modelMapper.map(category, CategoryRespond.class);
            categoryResponds.add(categoryRespond);
        }
        return categoryResponds;
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public Category insertNewCategory(CategoryDTO categoryDTO) {
        return categoryRepository.insertNewCategory(categoryDTO);
    }


    @Override
    public Category deleteCategoryById(Integer id) {
        return categoryRepository.deleteCategoryById(id);
    }

    @Override
    public Category updateCategoryById(Integer id, CategoryDTO categoryDTO) {
        return categoryRepository.updateCategoryById(id,categoryDTO);
    }


}
