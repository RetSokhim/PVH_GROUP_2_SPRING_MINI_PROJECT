package org.example.expense_tracking.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {
    private String userID;
    private String email;
    private String profileImage;
}
