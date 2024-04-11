package org.example.expense_tracking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.expense_tracking.model.entity.User;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpsDTO {
    private Integer otpsCode;
    private LocalDateTime issuedAt;
    private LocalDateTime expiration;
    private Integer verify;
    private Integer user;
}
