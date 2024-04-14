package org.example.expense_tracking.model.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otps {
    private Integer otpsId;
    private Integer otpsCode;
    private Timestamp issuedAt;
    private Timestamp expiration;
    private Integer verify;
    private User user;
}
