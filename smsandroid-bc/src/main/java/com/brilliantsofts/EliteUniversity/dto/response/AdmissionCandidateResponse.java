package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AdmissionCandidateResponse {
    private Long id;
    private String uniqueCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String applicationNumber;
    private String status;
    private Long appliedCourseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
