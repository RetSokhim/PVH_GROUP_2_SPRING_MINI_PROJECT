package org.example.expense_tracking.controller;

import org.apache.coyote.BadRequestException;
import org.example.expense_tracking.model.dto.request.UserLoginRequest;
import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserLoginTokenRespond;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.security.JwtService;
import org.example.expense_tracking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthController(UserService userService, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterRequest userRegisterRequest){
        UserRegisterResponse authRegister = userService.createNewUser(userRegisterRequest);
        return new ResponseEntity<>(authRegister,HttpStatus.CREATED);
    }
    @PutMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam Integer otp){
        userService.verifyAccount(otp);
        return new ResponseEntity<>("Your account is successfully verified",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody UserLoginRequest userLoginRequest) throws Exception {
        authenticate(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        final UserDetails userDetails = userService.loadUserByUsername(userLoginRequest.getEmail());
        final String token = jwtService.generateToken(userDetails);
        UserLoginTokenRespond authResponse = new UserLoginTokenRespond(token);
        return ResponseEntity.ok(authResponse);
    }
    private void authenticate(String email, String password) throws Exception {
        try {
            UserDetails userApp = userService.loadUserByUsername(email);
            if (userApp == null){throw new BadRequestException("Wrong Email");}
            if (!passwordEncoder.matches(password, userApp.getPassword())){
                throw new BadRequestException("Wrong Password");}
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);} catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);}
    }
}
