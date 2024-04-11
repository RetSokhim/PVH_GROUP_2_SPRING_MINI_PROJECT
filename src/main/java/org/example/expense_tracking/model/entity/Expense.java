package org.example.expense_tracking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private Integer expenseId;
    private Integer amount;
    private LocalDateTime date;
    private User user;
    private Category category;
}
