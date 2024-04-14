package org.example.expense_tracking.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please input valid email format")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Password must be 8 character long and mix between number and character")
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Password must be 8 character long and mix between number and character")
    @NotBlank(message = "Password cannot be blank")
    private String confirmPassword;

    @Pattern(regexp = "(\\S+(\\.(?i)(jpg|png|gif|bmp))$)",message = "Profile image must end with jpg, png, gif, bmp")
    @NotBlank(message = "Profile image cannot be blank")
    private String profileImage;
}
