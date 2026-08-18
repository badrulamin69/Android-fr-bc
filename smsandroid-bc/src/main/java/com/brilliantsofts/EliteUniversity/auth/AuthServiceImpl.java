package com.brilliantsofts.EliteUniversity.auth;

import com.brilliantsofts.EliteUniversity.entity.Applicant;
import com.brilliantsofts.EliteUniversity.entity.ResetPasswordToken;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.entity.VerificationToken;
import com.brilliantsofts.EliteUniversity.enums.ApplicationStatus;
import com.brilliantsofts.EliteUniversity.enums.UserRole;
import com.brilliantsofts.EliteUniversity.repository.ApplicantRepository;
import com.brilliantsofts.EliteUniversity.repository.ResetPasswordTokenRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.repository.VerificationTokenRepository;
import com.brilliantsofts.EliteUniversity.security.CustomUserDetails;
import com.brilliantsofts.EliteUniversity.security.JwtService;
import com.brilliantsofts.EliteUniversity.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ApplicantRepository applicantRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;

    private static final int VERIFICATION_TOKEN_EXPIRY_HOURS = 24;
    private static final int RESET_TOKEN_EXPIRY_MINUTES = 60;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid username/email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username/email or password");
        }

        if (!user.isEnabled()) {
            throw new DisabledException("Account is disabled");
        }

        if (!user.isAccountNonLocked()) {
            throw new LockedException("Account is locked");
        }

        if (!user.isAccountNonExpired()) {
            throw new BadCredentialsException("Account is expired");
        }

        if (!user.isCredentialsNonExpired()) {
            throw new BadCredentialsException("Credentials have expired");
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        request.getPassword()
                )
        );

        String accessToken = jwtService.generateToken(userDetails, user);
        String refreshToken = jwtService.generateRefreshToken(userDetails, user);

        return LoginResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .expiresIn(jwtService.getJwtExpiration())
                .build();
    }

    @Override
    @Transactional
    public LoginResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setEmailVerified(false);
        user.setRole(UserRole.APPLICANT);

        User savedUser = userRepository.save(user);

        // Create associated Applicant record
        Applicant applicant = new Applicant();
        applicant.setFullName(savedUser.getUsername());
        applicant.setPhone(savedUser.getPhone());
        applicant.setUser(savedUser);
        applicant.setStatus(ApplicationStatus.SUBMITTED);
        applicant.setApplicationNumber("APP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        applicantRepository.save(applicant);

        // Generate and send verification email
        String verificationToken = generateVerificationToken(savedUser);

        try {
            emailService.sendVerificationEmail(savedUser.getEmail(), savedUser.getUsername(), verificationToken);
        } catch (Exception e) {
            log.warn("Failed to send verification email to {}: {}", savedUser.getEmail(), e.getMessage());
        }

        CustomUserDetails userDetails = new CustomUserDetails(savedUser);
        String accessToken = jwtService.generateToken(userDetails, savedUser);
        String refreshToken = jwtService.generateRefreshToken(userDetails, savedUser);

        return LoginResponse.builder()
                .token(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .expiresIn(jwtService.getJwtExpiration())
                .build();
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String token = request.getRefreshToken();

        if (!jwtService.isRefreshToken(token)) {
            throw new BadCredentialsException("Invalid token: expected a refresh token");
        }

        String username = jwtService.extractUsername(token);

        if (username == null || jwtService.isTokenExpired(token)) {
            throw new BadCredentialsException("Invalid or expired refresh token");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!user.isEnabled() || !user.isAccountNonLocked()) {
            throw new DisabledException("Account is disabled or locked");
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);
        String newAccessToken = jwtService.generateToken(userDetails, user);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails, user);

        return LoginResponse.builder()
                .token(newAccessToken)
                .refreshToken(newRefreshToken)
                .tokenType("Bearer")
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .expiresIn(jwtService.getJwtExpiration())
                .build();
    }

    @Override
    @Transactional
    public void verifyEmail(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid verification token"));

        if (!verificationToken.isValid()) {
            if (verificationToken.isExpired()) {
                throw new IllegalArgumentException("Verification token has expired. Please request a new one.");
            }
            throw new IllegalArgumentException("Verification token has already been used");
        }

        User user = verificationToken.getUser();
        user.setEmailVerified(true);
        userRepository.save(user);

        verificationToken.setUsedAt(LocalDateTime.now());
        verificationTokenRepository.save(verificationToken);

        log.info("Email verified successfully for user: {}", user.getUsername());
    }

    @Override
    @Transactional
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User with specified email does not exist"));

        // Invalidate any existing reset tokens for this user
        resetPasswordTokenRepository.deleteByUserId(user.getId());

        // Generate new reset token
        String resetTokenValue = UUID.randomUUID().toString();
        ResetPasswordToken resetToken = ResetPasswordToken.builder()
                .token(resetTokenValue)
                .user(user)
                .expiresAt(LocalDateTime.now().plusMinutes(RESET_TOKEN_EXPIRY_MINUTES))
                .build();
        resetPasswordTokenRepository.save(resetToken);

        // Send password reset email
        try {
            emailService.sendPasswordResetEmail(user.getEmail(), user.getUsername(), resetTokenValue);
        } catch (Exception e) {
            log.warn("Failed to send password reset email to {}: {}", user.getEmail(), e.getMessage());
            throw new RuntimeException("Failed to send password reset email. Please try again later.");
        }
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        ResetPasswordToken resetToken = resetPasswordTokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> new IllegalArgumentException("Invalid password reset token"));

        if (!resetToken.isValid()) {
            if (resetToken.isExpired()) {
                throw new IllegalArgumentException("Password reset token has expired. Please request a new one.");
            }
            throw new IllegalArgumentException("Password reset token has already been used");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        resetToken.setUsedAt(LocalDateTime.now());
        resetPasswordTokenRepository.save(resetToken);

        // Send confirmation email
        try {
            emailService.sendPasswordResetConfirmation(user.getEmail(), user.getUsername());
        } catch (Exception e) {
            log.warn("Failed to send password reset confirmation email to {}: {}", user.getEmail(), e.getMessage());
        }

        log.info("Password reset successfully for user: {}", user.getUsername());
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request, String username) {
        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        log.info("Password changed successfully for user: {}", user.getUsername());
    }

    @Override
    public UserMeResponse getCurrentUser(String username) {
        User user = userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));

        return UserMeResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .emailVerified(user.isEmailVerified())
                .accountNonLocked(user.isAccountNonLocked())
                .accountNonExpired(user.isAccountNonExpired())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .build();
    }

    @Override
    public void logout(String token) {
        // Stateless JWT logout - tokens expire naturally
    }

    private String generateVerificationToken(User user) {
        // Delete any existing verification tokens for this user
        verificationTokenRepository.deleteByUserId(user.getId());

        String tokenValue = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder()
                .token(tokenValue)
                .user(user)
                .expiresAt(LocalDateTime.now().plusHours(VERIFICATION_TOKEN_EXPIRY_HOURS))
                .build();
        verificationTokenRepository.save(verificationToken);

        return tokenValue;
    }
}
