package org.example.expense_tracking.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterResponse {
    private Integer userID;
    private String email;
    private String profileImage;
}
