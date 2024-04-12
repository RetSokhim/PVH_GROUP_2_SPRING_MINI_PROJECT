package org.example.expense_tracking.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.expense_tracking.model.entity.Category;
import org.example.expense_tracking.model.entity.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExpenseRequest {
    private Integer amount;
    private String description;
    private LocalDateTime date;
    private User user;
    private Category categoryId;
}
