package org.example.expense_tracking.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseRequestDTO {
    @Positive(message = "Please input only positive number")
    @NotNull(message = "Amount cannot be null")
    private Integer amount;

    @NotBlank(message = "Please fill up the description")
    private String description;

    @NotNull(message = "date cannot be empty")
    private LocalDateTime Date;

    @NotNull(message = "category ID cannot be null")
    private UUID categoryId;

}
