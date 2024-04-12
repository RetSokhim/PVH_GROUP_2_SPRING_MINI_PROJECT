package org.example.expense_tracking.controller;

import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/expense")

public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/getAllExpense")
    public ResponseEntity<?> getAllExpense(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "2") Integer size) {
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Get All Expense",
                expenseService.getAllExpense(page, size),
                LocalDateTime.now(),
                201,
                HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }




}
