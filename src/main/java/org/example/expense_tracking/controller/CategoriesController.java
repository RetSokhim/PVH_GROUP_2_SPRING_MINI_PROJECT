package org.example.expense_tracking.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.model.dto.response.CategoryResponse;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.repository.UserRepository;
import org.example.expense_tracking.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
