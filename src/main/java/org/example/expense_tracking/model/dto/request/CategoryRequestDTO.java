package org.example.expense_tracking.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "Category's name cannot be blank")
    private String name;
    @NotBlank(message = "Category's description cannot be blank")
    private String description;
}
