package org.example.expense_tracking.service.serviceimplement;

import org.example.expense_tracking.exception.AccountVerificationException;
import org.example.expense_tracking.exception.OTPExpiredException;
import org.example.expense_tracking.exception.PasswordException;
import org.example.expense_tracking.exception.SearchNotFoundException;
import org.example.expense_tracking.model.dto.request.OtpsRequestDTO;
import org.example.expense_tracking.model.dto.request.UserPasswordRequest;
import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.model.dto.CustomUserDetail;
import org.example.expense_tracking.model.entity.Otps;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.repository.UserRepository;
import org.example.expense_tracking.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final OtpsServiceImpl otpsService;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, BCryptPasswordEncoder bCryptPasswordEncoder, OtpsServiceImpl otpsService, EmailService emailService) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.otpsService = otpsService;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findUserByEmail(email);
        return new CustomUserDetail(user);
    }

    @Override
    public UserRegisterResponse createNewUser(UserRegisterRequest userRegisterRequest) throws Exception {
        if (!userRegisterRequest.getPassword().equals(userRegisterRequest.getConfirmPassword())) {
            throw new PasswordException("Your password is not match with confirm password");
        }
        OtpsRequestDTO otps = otpsService.generateOtp();
        Context context = new Context();
        context.setVariable("message", String.valueOf(otps.getOtpsCode()));
        emailService.sendEmailWithHtmlTemplate(userRegisterRequest.getEmail(),
                "Here is your OTP code to verify",
                "email-template", context);
        String password = bCryptPasswordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(password);
        User user = userRepository.createNewUser(userRegisterRequest);
        otps.setUser(user.getUserId());
        otpsService.insertOtp(otps);
        return mapper.map(user, UserRegisterResponse.class);
    }

    public void verifyAccount(Integer otpVerify) throws OTPExpiredException, AccountVerificationException {
        Otps otps = otpsService.getOtpsByCode(otpVerify);
        if (otps != null) {
            if (otps.getExpiration().before(new Timestamp(System.currentTimeMillis()))) {
                throw new OTPExpiredException("OTP has expired.");
            }
            if (otps.getVerify() == 0) {
                otpsService.confirmVerify(otpVerify);
            } else {
                throw new AccountVerificationException("Your account has already been verified");
            }
        } else {
            throw new OTPExpiredException("Invalid OTP code");
        }
    }

    @Override
    public void resendOtpCode(String email) throws Exception {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new SearchNotFoundException("Cannot find your email account please register first");
        }
        OtpsRequestDTO otps = otpsService.generateOtp();
        Context context = new Context();
        context.setVariable("message", String.valueOf(otps.getOtpsCode()));
        emailService.sendEmailWithHtmlTemplate(email,
                "Here is your OTP code to verify",
                "email-template", context);
        otpsService.updateTheCodeAfterResend(otps, user.getUserId());
    }

    @Override
    public void resetPassword(UserPasswordRequest userPasswordRequest, String email) throws PasswordException {
        if (!userPasswordRequest.getPassword().equals(userPasswordRequest.getConfirmPassword())) {
            throw new PasswordException("Your password is not match with confirm password");
        }
        if (userRepository.findUserByEmail(email) != null) {
            String passwordEncode = bCryptPasswordEncoder.encode(userPasswordRequest.getPassword());
            userPasswordRequest.setPassword(passwordEncode);
            userRepository.resetPassword(userPasswordRequest, email);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
