package org.example.expense_tracking.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryExpenseResponse {
    private UUID categoryId;
    private String name;
    private String description;
}
