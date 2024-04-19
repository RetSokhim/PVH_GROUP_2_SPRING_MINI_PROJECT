package org.example.expense_tracking.controller;

import lombok.AllArgsConstructor;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.service.ExpenseService;
import org.example.expense_tracking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<?> deleteExpense(@PathVariable Integer expenseId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        expenseService.deleteExpenseByID(expenseId, user.getUserId());
        return ResponseEntity.ok(
                ApiResponse.builder().message("Deleted expense with ID f8731b3c-8979-48db-b87a-6a5f5b23307e successfully")
                        .time(LocalDateTime.now())
                        .code(200)
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
