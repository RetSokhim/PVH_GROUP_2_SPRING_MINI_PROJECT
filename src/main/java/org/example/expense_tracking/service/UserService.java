package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.UserPasswordRequest;
import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRegisterResponse createNewUser(UserRegisterRequest userRegisterRequest);
    void verifyAccount(Integer otpVerify);
    void resendOtpCode (String email);
    void resetPassword(UserPasswordRequest userPasswordRequest,String email);
}
