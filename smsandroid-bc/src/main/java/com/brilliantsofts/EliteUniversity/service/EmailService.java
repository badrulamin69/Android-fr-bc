package com.brilliantsofts.EliteUniversity.service;

public interface EmailService {
    void sendVerificationEmail(String to, String username, String verificationToken);
    void sendPasswordResetEmail(String to, String username, String resetToken);
    void sendPasswordResetConfirmation(String to, String username);
}
