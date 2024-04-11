package org.example.expense_tracking.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
    private String profileImage;
}
