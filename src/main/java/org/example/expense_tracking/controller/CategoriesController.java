package org.example.expense_tracking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example.expense_tracking.exception.PageLimitException;
import org.example.expense_tracking.exception.SearchNotFoundException;
import org.example.expense_tracking.model.dto.request.CategoryRequestDTO;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.model.dto.response.CategoryResponse;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.service.CategoryService;
import org.example.expense_tracking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoriesController {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoriesController(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping()
    @Operation(summary = "Get all categories")
    public ResponseEntity<?> getAllCategory(@RequestParam(defaultValue = "5") @Positive(message = "size cannot be negative or 0") Integer size,
                                            @RequestParam(defaultValue = "1") @Positive(message = "page cannot be negative or 0") Integer page) throws SearchNotFoundException, PageLimitException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);

        Integer offset = (page - 1) * size;
        Integer totalCategories = categoryService.getTotalCategories(user.getUserId());
        int totalPages = (int)Math.ceil((double) totalCategories/size);
        if(totalCategories == 0){
            throw new SearchNotFoundException("There is no category to show please add some");
        }
        if(page > totalPages){
            throw new PageLimitException("Page cannot be greater than total page");
        }
        List<CategoryResponse> userCategories = categoryService.getAllCategories(user.getUserId(),size,offset);
        ApiResponse<?> apiResponse = new ApiResponse<>("Get all categories successfully",
                userCategories, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    @Operation(summary = "Get category By ID")
    public ResponseEntity<?> getCategoryById(@PathVariable UUID categoryId) throws SearchNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        CategoryResponse userCategory = categoryService.getCategoryById(categoryId, user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>("Get category with ID " + categoryId + " successfully",
                userCategory, LocalDateTime.now(), 200, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    @Operation(summary = "Delete category by ID")
    public ResponseEntity<?> deleteCategoryById(@PathVariable UUID categoryId) throws SearchNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        categoryService.deleteCategoryById(categoryId, user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>("Delete category with ID " + categoryId + " successfully",
                null, LocalDateTime.now(), 200, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping()
    @Operation(summary = "Insert new category")
    public ResponseEntity<?> insertNewCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        CategoryResponse userCategory = categoryService.insertNewCategory(categoryRequestDTO);
        ApiResponse<?> apiResponse = new ApiResponse<>("New category has been added successfully",
                userCategory, LocalDateTime.now(), 200, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    @Operation(summary = "Update category by ID")
    public ResponseEntity<?> updateCategoryById(@PathVariable UUID categoryId,
                                                @Valid @RequestBody CategoryRequestDTO categoryRequestDTO) throws SearchNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        CategoryResponse categoryResponse = categoryService.updateCategoryById(categoryId, categoryRequestDTO, user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>("updated category with ID " + categoryId + " successfully",
                categoryResponse, LocalDateTime.now(), 200, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
