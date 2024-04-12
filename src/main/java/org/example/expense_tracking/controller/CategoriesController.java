package org.example.expense_tracking.controller;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/category")
@SecurityRequirement(name = "bearerAuth")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory (){
        ApiResponse<?> apiResponse = new ApiResponse<>("asdhasdasdba",
                categoryService.getAllCategories(), LocalDateTime.now(),201,HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
