package org.example.expense_tracking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.example.expense_tracking.model.dto.request.ExpenseRequestDTO;
import org.example.expense_tracking.model.dto.response.ApiResponse;
import org.example.expense_tracking.model.dto.response.ExpenseResponse;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.service.ExpenseService;
import org.example.expense_tracking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/expense")
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    public ExpenseController(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
    }

    @GetMapping()
    @Operation(summary = "Get all expenses")
    public ResponseEntity<?> getAllExpense(@RequestParam(defaultValue = "5") @Positive(message = "size cannot be negative or 0") Integer size,
                                           @RequestParam(defaultValue = "1") @Positive(message = "page cannot be negative or 0") Integer page,
                                           @RequestParam(defaultValue = "expense_id") @NotBlank(message = "order by cannot be blank") String orderBy,
                                           @RequestParam(defaultValue = "false") Boolean ascOrDesc
                                           ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        Integer offset = (page - 1) * size;
        String sortBy;
        if(ascOrDesc){
            sortBy = "DESC";
        }else {
            sortBy = "ASC";
        }
        List<ExpenseResponse> userExpense = expenseService.getAllExpense(user.getUserId(),size,offset,orderBy,sortBy);
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Get all expenses successfully",
                userExpense, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{expenseId}")
    @Operation(summary = "Get expense By ID")
    public ResponseEntity<?> getExpenseById(@PathVariable UUID expenseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        ExpenseResponse userExpense = expenseService.getExpenseById(expenseId, user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Get expense with ID "+expenseId+" successfully",
                userExpense, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{expenseId}")
    @Operation(summary = "Delete expense By ID")
    public ResponseEntity<?> deleteExpenseById(@PathVariable UUID expenseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        expenseService.deleteExpenseById(expenseId, user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>(
                "Deleted expense with ID "+expenseId+" successfully",
                null, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Insert new expense")
    public ResponseEntity<?> insertNewExpense(@Valid @RequestBody ExpenseRequestDTO expenseRequestDTO) {
        ExpenseResponse expenseResponse = expenseService.insertNewExpense(expenseRequestDTO);
        ApiResponse<?> apiResponse = new ApiResponse<>("New expense has been added successfully",
                expenseResponse, LocalDateTime.now(), 201, HttpStatus.CREATED);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{expenseId}")
    @Operation(summary = "Update expense By ID")
    public ResponseEntity<?> updateExpenseById(@PathVariable UUID expenseId,
                                               @Valid @RequestBody ExpenseRequestDTO expenseRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findUserByEmail(email);
        ExpenseResponse userExpense = expenseService.updateExpenseById(expenseId, expenseRequestDTO, user.getUserId());
        ApiResponse<?> apiResponse = new ApiResponse<>("Updated expense with ID "+expenseId+" successfully",
                userExpense, LocalDateTime.now(), 201, HttpStatus.OK);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
