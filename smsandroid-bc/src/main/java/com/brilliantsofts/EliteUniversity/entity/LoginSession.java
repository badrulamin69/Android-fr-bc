package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_sessions")
@Data
public class LoginSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String sessionToken;

    private String ipAddress;

    private String browser;

    private String operatingSystem;

    private String deviceType;

    private LocalDateTime loginTime;

    private LocalDateTime lastActivityTime;

    private LocalDateTime logoutTime;

    private boolean isActive;

    private boolean expired;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
