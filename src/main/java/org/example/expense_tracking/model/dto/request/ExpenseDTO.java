package org.example.expense_tracking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTO {
    private String amount;
    private String description;
    private LocalDateTime Date;
    private List<Integer> category_id;

}
