package org.example.expense_tracking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

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
