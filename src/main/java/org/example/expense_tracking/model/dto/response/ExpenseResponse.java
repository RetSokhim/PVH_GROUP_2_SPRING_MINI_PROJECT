package org.example.expense_tracking.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseResponse {
    private UUID expenseId;
    private Integer amount;
    private String description;
    private LocalDateTime date;
    private UserRegisterResponse user;
    private CategoryExpenseResponse category;
}
