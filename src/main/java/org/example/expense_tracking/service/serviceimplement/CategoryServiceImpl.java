package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.model.dto.request.CategoryDTO;
import org.example.expense_tracking.model.dto.response.CategoryResponse;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.model.entity.Category;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.repository.CategoryRepository;
import org.example.expense_tracking.repository.UserRepository;
import org.example.expense_tracking.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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

    @Override
    public CategoryResponse getCategoryById(Integer id, Integer userId) {
        Category category = categoryRepository.getCategoryById(id,userId);
        return modelMapper.map(category,CategoryResponse.class);
    }

    @Override
    public void deleteCategoryById(Integer id, Integer userId) {
        categoryRepository.deleteCategoryById(id,userId);
    }

    @Override
    public CategoryResponse insertNewCategory(CategoryDTO categoryDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setUser(user);

        Category categoryAfterInsertIntoDatabase = categoryRepository.insertNewCategory(category);
        UserRegisterResponse userRegisterResponse = modelMapper.map(user, UserRegisterResponse.class);

        CategoryResponse categoryResponse = modelMapper.map(categoryAfterInsertIntoDatabase, CategoryResponse.class);
        categoryResponse.setUser(userRegisterResponse);

        return categoryResponse;
    }

    @Override
    public CategoryResponse updateCategoryById(Integer id, CategoryDTO categoryDTO, Integer userId) {
        Category category = categoryRepository.updateCategoryById(id,categoryDTO,userId);
        return modelMapper.map(category,CategoryResponse.class);
    }

}
