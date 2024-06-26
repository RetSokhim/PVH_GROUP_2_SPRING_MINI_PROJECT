package org.example.expense_tracking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private UUID expenseId;
    private Integer amount;
    private String description;
    private LocalDateTime date;
    private User user;
    private Category category;
}
