package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PreAdmissionResponse {
    private Long id;
    private String registrationNumber;
    private String trackingNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String bloodGroup;
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
    private String status;
    private String remarks;
    private boolean emailVerified;
    private Long sessionId;
    private Long circularId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
