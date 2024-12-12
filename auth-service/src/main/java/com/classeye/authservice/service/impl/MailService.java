package com.classeye.authservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendHtmlEmail(String to, String subject, String htmlContent) {
        log.info("Sending email to: {}", to);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("noreply@sandboxef78bc798ab847fdbcb10955b9f9d821.mailgun.org");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // Enable HTML content
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }

    public void sendAccountCreationEmail(String to, String username, String tempPassword) {
        log.info("Sending account creation email to: {}", to);
        String subject = "Welcome to ClassEye - Your Account Details";
        String htmlContent = String.format(
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>" +
                        "<h2 style='color: #2a9d8f;'>Welcome, %s!</h2>" +
                        "<p>Your account has been successfully created. Here are your temporary login details:</p>" +
                        "<p><strong>Temporary Password:</strong> %s</p>" +
                        "<p style='margin-top: 20px;'>For your security, please update your password after logging in.</p>" +
                        "<hr style='border: none; border-top: 1px solid #ddd;' />" +
                        "<p style='font-size: 0.9em; color: #555;'>If you have any questions, feel free to contact us at support@classeye.com.</p>" +
                        "</body>" +
                        "</html>",
                username, tempPassword
        );
        sendHtmlEmail(to, subject, htmlContent);
    }

    public void sendRoleBasedEmail(String to, String role, String subject, String messageContent) {
        log.info("Sending role-based email to: {}", to);
        String htmlContent = String.format(
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>" +
                        "<h2 style='color: #2a9d8f;'>Important Update for %s Role</h2>" +
                        "<p>%s</p>" +
                        "<p style='margin-top: 20px;'>Thank you for being part of our team!</p>" +
                        "<hr style='border: none; border-top: 1px solid #ddd;' />" +
                        "<p style='font-size: 0.9em; color: #555;'>For support, contact us at support@classeye.com.</p>" +
                        "</body>" +
                        "</html>",
                role, messageContent
        );
        sendHtmlEmail(to, subject, htmlContent);
    }

    public void sendPasswordResetEmail(String email, String newPassword) {
        log.info("Sending password reset email to: {}", email);
        String subject = "Password Reset Request";
        String htmlContent = String.format(
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; line-height: 1.6; color: #333;'>" +
                        "<h2 style='color: #e76f51;'>Password Reset</h2>" +
                        "<p>Your password has been successfully reset. Here is your new password:</p>" +
                        "<p><strong>New Password:</strong> %s</p>" +
                        "<p style='margin-top: 20px;'>Please update your password after logging in for security purposes.</p>" +
                        "<hr style='border: none; border-top: 1px solid #ddd;' />" +
                        "<p style='font-size: 0.9em; color: #555;'>If you didn’t request this, please contact us immediately at support@classeye.com.</p>" +
                        "</body>" +
                        "</html>",
                newPassword
        );
        sendHtmlEmail(email, subject, htmlContent);
    }
}
