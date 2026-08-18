package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StudentProfileResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String nationality;
    private String bloodGroup;
    private String emergencyContact;
    private String emergencyContactName;
    private String medicalInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Enriched student details
    private String studentName;
    private String studentCode;
    private String email;
    private String phone;
    private String programName;
    private String departmentName;
}
