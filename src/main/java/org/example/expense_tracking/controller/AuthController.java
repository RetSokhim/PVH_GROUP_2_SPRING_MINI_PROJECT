package org.example.expense_tracking.controller;

import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.service.UserService;
import org.example.expense_tracking.service.serviceimplement.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterRequest userRegisterRequest){
        UserRegisterResponse authRegister = userService.createNewUser(userRegisterRequest);
        return new ResponseEntity<>(authRegister,HttpStatus.CREATED);
    }
    private final EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendMail();
        return "Email sent successfully";
    }
}
