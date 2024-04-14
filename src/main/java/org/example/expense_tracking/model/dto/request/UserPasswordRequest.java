package org.example.expense_tracking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordRequest {
    private String password;
    private String confirmPassword;
}
