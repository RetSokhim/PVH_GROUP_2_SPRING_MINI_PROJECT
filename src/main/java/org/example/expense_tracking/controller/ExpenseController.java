package org.example.expense_tracking.controller;

import org.example.expense_tracking.model.dto.request.ExpenseRequest;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.model.dto.response.ExpenseDto;
import org.example.expense_tracking.model.entity.Expense;
import org.example.expense_tracking.service.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping("/api/v1/expenses")
    public ResponseEntity<ApiResponse<ExpenseDto>> createExpense(@RequestBody ExpenseDto expenseDto){
        ExpenseDto expenseDto1 = expenseService.createExpense(expenseDto);
        return ResponseEntity.ok(
                ApiResponse.<ExpenseDto>builder()
                        .message("Successfully created expense")
                        .status(HttpStatus.CREATED)
                        .code(201)
                        .payload(expenseDto1)
                        .build()
        );
    }
}
