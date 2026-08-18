package com.brilliantsofts.EliteUniversity.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username:}")
    private String fromEmail;

    @Value("${app.verification.url:http://localhost:8085/api/auth/verify-email}")
    private String verificationUrl;

    @Value("${app.reset-password.url:http://localhost:4200/reset-password}")
    private String resetPasswordUrl;

    @Value("${app.frontend.url:http://localhost:4200}")
    private String frontendUrl;

    @Override
    @Async
    public void sendVerificationEmail(String to, String username, String verificationToken) {
        String subject = "Verify Your Email - EliteUniversity";
        String verificationLink = verificationUrl + "?token=" + verificationToken;

        String htmlContent = buildVerificationEmailHtml(username, verificationLink);

        sendHtmlEmail(to, subject, htmlContent);
        log.info("Verification email sent to: {}", to);
    }

    @Override
    @Async
    public void sendPasswordResetEmail(String to, String username, String resetToken) {
        String subject = "Reset Your Password - EliteUniversity";
        String resetLink = resetPasswordUrl + "?token=" + resetToken;

        String htmlContent = buildPasswordResetEmailHtml(username, resetLink);

        sendHtmlEmail(to, subject, htmlContent);
        log.info("Password reset email sent to: {}", to);
    }

    @Override
    @Async
    public void sendPasswordResetConfirmation(String to, String username) {
        String subject = "Password Reset Successful - EliteUniversity";
        String htmlContent = buildPasswordResetConfirmationHtml(username);

        sendHtmlEmail(to, subject, htmlContent);
        log.info("Password reset confirmation email sent to: {}", to);
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }

    private String buildVerificationEmailHtml(String username, String verificationLink) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
                        .container { max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
                        .header { background-color: #1a237e; color: #ffffff; padding: 30px; text-align: center; }
                        .header h1 { margin: 0; font-size: 24px; }
                        .content { padding: 30px; color: #333333; line-height: 1.6; }
                        .content h2 { color: #1a237e; margin-top: 0; }
                        .btn { display: inline-block; background-color: #1a237e; color: #ffffff !important; text-decoration: none; padding: 14px 30px; border-radius: 5px; font-weight: bold; margin: 20px 0; }
                        .btn:hover { background-color: #283593; }
                        .footer { background-color: #f8f9fa; padding: 20px; text-align: center; color: #666666; font-size: 12px; border-top: 1px solid #eeeeee; }
                        .warning { color: #d32f2f; font-weight: bold; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>EliteUniversity</h1>
                        </div>
                        <div class="content">
                            <h2>Welcome, %s!</h2>
                            <p>Thank you for registering at EliteUniversity. Please verify your email address by clicking the button below.</p>
                            <p style="text-align: center;">
                                <a href="%s" class="btn">Verify Email Address</a>
                            </p>
                            <p>If the button doesn't work, copy and paste this link into your browser:</p>
                            <p style="word-break: break-all; color: #1a237e;">%s</p>
                            <p class="warning">This verification link will expire in 24 hours.</p>
                            <p>If you did not create an account, please ignore this email.</p>
                        </div>
                        <div class="footer">
                            <p>&copy; %d EliteUniversity. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(username, verificationLink, verificationLink, java.time.Year.now().getValue());
    }

    private String buildPasswordResetEmailHtml(String username, String resetLink) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
                        .container { max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
                        .header { background-color: #c62828; color: #ffffff; padding: 30px; text-align: center; }
                        .header h1 { margin: 0; font-size: 24px; }
                        .content { padding: 30px; color: #333333; line-height: 1.6; }
                        .content h2 { color: #c62828; margin-top: 0; }
                        .btn { display: inline-block; background-color: #c62828; color: #ffffff !important; text-decoration: none; padding: 14px 30px; border-radius: 5px; font-weight: bold; margin: 20px 0; }
                        .btn:hover { background-color: #d32f2f; }
                        .footer { background-color: #f8f9fa; padding: 20px; text-align: center; color: #666666; font-size: 12px; border-top: 1px solid #eeeeee; }
                        .warning { color: #d32f2f; font-weight: bold; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>EliteUniversity</h1>
                        </div>
                        <div class="content">
                            <h2>Password Reset Request</h2>
                            <p>Hello %s,</p>
                            <p>We received a request to reset your password. Click the button below to set a new password.</p>
                            <p style="text-align: center;">
                                <a href="%s" class="btn">Reset Password</a>
                            </p>
                            <p>If the button doesn't work, copy and paste this link into your browser:</p>
                            <p style="word-break: break-all; color: #c62828;">%s</p>
                            <p class="warning">This password reset link will expire in 1 hour.</p>
                            <p>If you did not request a password reset, please ignore this email. Your password will remain unchanged.</p>
                        </div>
                        <div class="footer">
                            <p>&copy; %d EliteUniversity. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(username, resetLink, resetLink, java.time.Year.now().getValue());
    }

    private String buildPasswordResetConfirmationHtml(String username) {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }
                        .container { max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
                        .header { background-color: #2e7d32; color: #ffffff; padding: 30px; text-align: center; }
                        .header h1 { margin: 0; font-size: 24px; }
                        .content { padding: 30px; color: #333333; line-height: 1.6; }
                        .content h2 { color: #2e7d32; margin-top: 0; }
                        .footer { background-color: #f8f9fa; padding: 20px; text-align: center; color: #666666; font-size: 12px; border-top: 1px solid #eeeeee; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h1>EliteUniversity</h1>
                        </div>
                        <div class="content">
                            <h2>Password Reset Successful</h2>
                            <p>Hello %s,</p>
                            <p>Your password has been successfully reset. You can now log in with your new password.</p>
                            <p>If you did not make this change, please contact our support team immediately.</p>
                        </div>
                        <div class="footer">
                            <p>&copy; %d EliteUniversity. All rights reserved.</p>
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(username, java.time.Year.now().getValue());
    }
}
