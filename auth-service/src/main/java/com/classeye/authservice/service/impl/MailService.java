package com.classeye.authservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        log.info("Sending email to: {}", to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@sandboxef78bc798ab847fdbcb10955b9f9d821.mailgun.org");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendAccountCreationEmail(String to, String username, String tempPassword) {
        log.info("Sending account creation email to: {}", to);
        String subject = "Account Creation - Your Temporary Password";
        String text = String.format("Hello %s,\n\nYour account has been created. Use the temporary password below to log in:\n\nTemporary Password: %s\n\nBest regards,\nThe Team", username, tempPassword);
        sendSimpleEmail(to, subject, text);
    }

    public void sendRoleBasedEmail(String to, String role, String subject, String messageContent) {
        String subjectWithRole = String.format("%s - Role: %s", subject, role);
        String body = String.format("Dear %s,\n\n%s\n\nBest regards,\nThe Team", role, messageContent);
        sendSimpleEmail(to, subjectWithRole, body);
    }

    public void sendPasswordResetEmail(String email, String newPassword) {
        log.info("Sending password reset email to: {}", email);
        String subject = "Password Reset - New Password";
        String text = String.format("Hello,\n\nYour password has been reset. Use the new password below to log in:\n\nNew Password: %s\n\nBest regards,\nThe Team", newPassword);
        sendSimpleEmail(email, subject, text);
    }
}
