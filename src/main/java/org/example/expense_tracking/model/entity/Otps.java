package org.example.expense_tracking.model.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Otps {
    private Integer otpsId;
    private Integer otpsCode;
    private String issuedAt;
    private LocalDateTime expiration;
    private Integer verify;
    private User user;
}
