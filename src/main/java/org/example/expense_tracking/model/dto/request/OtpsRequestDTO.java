package org.example.expense_tracking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpsRequestDTO {
    private Integer otpsCode;
    private Timestamp issuedAt;
    private Timestamp expiration;
    private Integer verify;
    private UUID user;
}
