package org.example.expense_tracking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpsRequest {
    private Integer otpsCode;
    private Timestamp issuedAt;
    private Timestamp expiration;
    private Integer verify;
    private Integer user;
}
