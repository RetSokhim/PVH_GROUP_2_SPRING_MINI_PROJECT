package org.example.expense_tracking.service.serviceimplement;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    //    send normal text email
//    public void sendEmailOtp(String email,String otpCode){
//        try {
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom("retsokhim2001@gmail.com");
//            helper.setTo(email);
//            helper.setSubject("Here is your OTP to verify");
//            helper.setText(otpCode);
//            javaMailSender.send(message);
//        } catch (MailException | MessagingException ex) {
//            System.err.println("Error sending email: " + ex.getMessage());
//        }
//    }
    //send html template email
    public void sendEmailWithHtmlTemplate(String toEmail, String subject, String templateName, Context context) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setFrom("retsokhim2001@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            String htmlContent = templateEngine.process(templateName, context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
