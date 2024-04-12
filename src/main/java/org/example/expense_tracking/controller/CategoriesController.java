package org.example.expense_tracking.controller;

import org.example.expense_tracking.model.dto.request.CategoryDTO;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
@RestController
@RequestMapping("v1/category")
public class CategoriesController {
    private final CategoryService categoryService;

    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCategory(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "2") Integer size) {
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Get All Category",
                categoryService.getAllCategories(page, size),
                LocalDateTime.now(),
                201,
                HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Get Category By ID",
                categoryService.getCategoryById(id),
                LocalDateTime.now(),
                201,
                HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/insertNewCategory")
    public ResponseEntity<?> insertNewCategory(@RequestBody CategoryDTO categoryDTO) {
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Insert was Successful",
                categoryService.insertNewCategory(categoryDTO),
                LocalDateTime.now(),
                201,
                HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/UpdateCategoryById/{id}")
    public ResponseEntity<?> updateCategoryById(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        ApiResponse<?> apiResponse = new ApiResponse<>(
                    "Update Category By ID",
                    categoryService.updateCategoryById(id,categoryDTO),
                    LocalDateTime.now(),
                    201,
                    HttpStatus.OK);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

    //DeleteCategoryById
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Delete Category Was Successful",
                categoryService.deleteCategoryById(id),
                LocalDateTime.now(),
                201,
                HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}

