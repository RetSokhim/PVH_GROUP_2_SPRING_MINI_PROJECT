package org.example.expense_tracking.service;

import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserRegisterResponse createNewUser(UserRegisterRequest userRegisterRequest);
}
