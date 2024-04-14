package org.example.expense_tracking.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.expense_tracking.model.dto.request.CategoryDTO;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.model.dto.response.CategoryResponse;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.repository.UserRepository;
import org.example.expense_tracking.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("v1/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoriesController {
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    public CategoriesController(CategoryService categoryService, UserRepository userRepository) {
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        List<CategoryResponse> userCategories = categoryService.getAllCategories(user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>("Get all categories successfully",
                userCategories, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        CategoryResponse userCategory = categoryService.getCategoryById(id,user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>("Get category by ID successfully",
                userCategory, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        categoryService.deleteCategoryById(id,user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>("delete category successfully",
                null, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<?> insertNewCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryResponse userCategory = categoryService.insertNewCategory(categoryDTO);
        ApiResponse<?> apiResponse = new ApiResponse<>("delete category successfully",
                userCategory, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Integer id,@RequestBody CategoryDTO categoryDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findUserByEmail(email);
        CategoryResponse categoryResponse = categoryService.updateCategoryById(id, categoryDTO, user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>("delete category successfully",
                categoryResponse, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
