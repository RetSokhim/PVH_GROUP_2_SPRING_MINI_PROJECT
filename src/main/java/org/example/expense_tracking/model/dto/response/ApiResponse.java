package org.example.expense_tracking.model.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private String message;
    private T payload;
    private LocalDateTime time;
    private Integer code;
    private HttpStatus status;
}
