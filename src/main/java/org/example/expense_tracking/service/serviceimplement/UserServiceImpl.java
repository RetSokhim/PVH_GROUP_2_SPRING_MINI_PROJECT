package org.example.expense_tracking.service.serviceimplement;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.expense_tracking.model.dto.request.OtpsDTO;
import org.example.expense_tracking.model.dto.request.UserRegisterRequest;
import org.example.expense_tracking.model.dto.response.UserRegisterResponse;
import org.example.expense_tracking.model.dto.CustomUserDetail;
import org.example.expense_tracking.model.entity.User;
import org.example.expense_tracking.repository.OtpsRepository;
import org.example.expense_tracking.repository.UserRepository;
import org.example.expense_tracking.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;
    private final OtpsServiceImpl otpsService;
    private final OtpsRepository otpsRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, BCryptPasswordEncoder bCryptPasswordEncoder, JavaMailSender javaMailSender, OtpsServiceImpl otpsService, OtpsRepository otpsRepository) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.javaMailSender = javaMailSender;
        this.otpsService = otpsService;
        this.otpsRepository = otpsRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        return new CustomUserDetail(user);
    }
    @Override
    public UserRegisterResponse createNewUser(UserRegisterRequest userRegisterRequest) {
        OtpsDTO otps = otpsService.generateOtp();
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("retsokhim2001@gmail.com");
            helper.setTo(userRegisterRequest.getEmail());
            helper.setSubject("Here is your OTP to verify");
            helper.setText(String.valueOf(otps.getOtpsCode()));
            javaMailSender.send(message);
        } catch (MailException | MessagingException ex) {
            System.err.println("Error sending email: " + ex.getMessage());
        }
        String password = bCryptPasswordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(password);
        User user = userRepository.createNewUser(userRegisterRequest);
        otps.setUser(user.getUserId());
        otpsRepository.insertOtp(otps);
        return mapper.map(user, UserRegisterResponse.class);
    }
    @Override
    public void verifyAccount(Integer otpVerify) {
        otpsRepository.confirmVerify(otpVerify);
    }
    @Override
    public void resendOtpCode(String email) {
        User user = userRepository.findUserByEmail(email);
        OtpsDTO otps = otpsService.generateOtp();
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("retsokhim2001@gmail.com");
            helper.setTo(user.getEmail());
            helper.setSubject("Here is your OTP to verify");
            helper.setText(String.valueOf(otps.getOtpsCode()));
            javaMailSender.send(message);
        } catch (MailException | MessagingException ex) {
            System.err.println("Error sending email: " + ex.getMessage());
        }
        otpsRepository.updateTheCodeAfterResend(otps,user.getUserId());
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
