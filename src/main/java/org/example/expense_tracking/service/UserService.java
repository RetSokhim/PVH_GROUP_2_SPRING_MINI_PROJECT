package org.example.expense_tracking.service;

import org.example.expense_tracking.exception.AccountVerificationException;
import org.example.expense_tracking.exception.OTPExpiredException;
import org.example.expense_tracking.exception.PasswordException;
import org.example.expense_tracking.model.dto.request.UserPasswordRequest;
import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRegisterResponse createNewUser(UserRegisterRequest userRegisterRequest) throws Exception;
    void verifyAccount(Integer otpVerify) throws OTPExpiredException, AccountVerificationException;
    void resendOtpCode (String email) throws Exception;
    void resetPassword(UserPasswordRequest userPasswordRequest,String email) throws PasswordException;
    User findUserByEmail(String email);
}
