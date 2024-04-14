package org.example.expense_tracking.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Integer categoryId;
    private String name;
    private String description;
    private UserRegisterResponse user;
}
