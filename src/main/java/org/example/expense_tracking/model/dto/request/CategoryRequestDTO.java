package org.example.expense_tracking.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers are allowed")
    @NotBlank(message = "Category's name cannot be blank")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Only letters and numbers are allowed")
    @NotBlank(message = "Category's description cannot be blank")
    private String description;
}
