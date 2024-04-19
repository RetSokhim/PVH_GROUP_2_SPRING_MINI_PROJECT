package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRegisterResponse createNewUser(UserRegisterRequest userRegisterRequest);
    void verifyAccount(Integer otpVerify);
    void resendOtpCode (String email);

    User findUserByEmail(String email);
}
