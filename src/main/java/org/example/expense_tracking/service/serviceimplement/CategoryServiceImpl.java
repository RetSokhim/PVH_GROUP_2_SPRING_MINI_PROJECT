package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.exception.SearchNotFoundException;
import org.example.expense_tracking.model.dto.request.CategoryRequestDTO;
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
import java.util.UUID;

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
    public List<CategoryResponse> getAllCategories(UUID userId, Integer size, Integer offset) {
        List<Category> categories = categoryRepository.getAllCategories(userId,size,offset);
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse categoryResponse = modelMapper.map(category, CategoryResponse.class);
            categoryResponses.add(categoryResponse);
        }
        return categoryResponses;
    }

    @Override
    public CategoryResponse getCategoryById(UUID categoryId, UUID userId) throws SearchNotFoundException {
        Category category = categoryRepository.getCategoryById(categoryId,userId);
        if(category == null){
            throw new SearchNotFoundException("Category with ID "+categoryId+" is not found");
        }
        return modelMapper.map(category,CategoryResponse.class);
    }

    @Override
    public void deleteCategoryById(UUID categoryId, UUID userId) throws SearchNotFoundException {
        if(categoryRepository.getCategoryById(categoryId,userId) == null){
            throw new SearchNotFoundException("Category with ID "+categoryId+" is not found");
        }
        categoryRepository.deleteCategoryById(categoryId,userId);
    }

    @Override
    public CategoryResponse insertNewCategory(CategoryRequestDTO categoryRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);

        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        category.setUser(user);

        Category categoryAfterInsertIntoDatabase = categoryRepository.insertNewCategory(category);
        UserRegisterResponse userRegisterResponse = modelMapper.map(user, UserRegisterResponse.class);

        CategoryResponse categoryResponse = modelMapper.map(categoryAfterInsertIntoDatabase, CategoryResponse.class);
        categoryResponse.setUser(userRegisterResponse);

        return categoryResponse;
    }

    @Override
    public CategoryResponse updateCategoryById(UUID categoryId, CategoryRequestDTO categoryRequestDTO, UUID userId) throws SearchNotFoundException {
        if(categoryRepository.getCategoryById(categoryId,userId) == null){
            throw new SearchNotFoundException("Category with ID "+categoryId+" is not found");
        }
        Category category = categoryRepository.updateCategoryById(categoryId, categoryRequestDTO,userId);
        return modelMapper.map(category,CategoryResponse.class);
    }

    @Override
    public Integer getTotalCategories(UUID userId) {
        return categoryRepository.getTotalCategory(userId);
    }

}
