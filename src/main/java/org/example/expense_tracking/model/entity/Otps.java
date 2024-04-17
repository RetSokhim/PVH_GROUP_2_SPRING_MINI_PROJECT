package org.example.expense_tracking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Otps {
    private UUID otpsId;
    private Integer otpsCode;
    private Timestamp issuedAt;
    private Timestamp expiration;
    private Integer verify;
    private User user;
}
