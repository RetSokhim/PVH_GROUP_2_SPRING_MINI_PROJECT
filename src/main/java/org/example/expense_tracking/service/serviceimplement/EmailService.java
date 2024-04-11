package org.example.expense_tracking.service.serviceimplement;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendMail() {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("retsokhim2001@gmai.com");
            helper.setTo("sreaksa492@gmail.com");
            helper.setSubject("Fuck You seth");
            helper.setText("This is a test email.");
            javaMailSender.send(message);
        } catch (MailException | MessagingException ex) {
            System.err.println("Error sending email: " + ex.getMessage());
        }
    }
}
