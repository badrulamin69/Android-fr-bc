package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pre_admission_registrations")
@Data
public class PreAdmissionRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String registrationNumber;

    @Column(unique = true, nullable = false)
    private String trackingNumber;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private LocalDate dateOfBirth;

    private String gender;

    private String bloodGroup;

    @Column(length = 1000)
    private String address;

    private String fatherName;

    private String motherName;

    private String guardianPhone;

    private String photoUrl;

    private String signatureUrl;

    private Double sscGpa;

    private Integer sscYear;

    private String sscBoard;

    private Double hscGpa;

    private Integer hscYear;

    private String hscBoard;

    private String programPreference1;

    private String programPreference2;

    private String programPreference3;

    @Column(nullable = false)
    private String status = "SUBMITTED";

    private String remarks;

    private boolean emailVerified = false;

    private Long sessionId;

    private Long circularId;

    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

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
