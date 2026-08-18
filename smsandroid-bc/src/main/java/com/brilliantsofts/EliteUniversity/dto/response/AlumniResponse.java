package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlumniResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private LocalDate graduationDate;
    private String degree;
    private Long programId;
    private Long departmentId;
    private String currentCompany;
    private String currentDesignation;
    private String currentLocation;
    private String email;
    private String phone;
    private String linkedInProfile;
    private Boolean isAvailableForMentoring;
    private Boolean isAvailableForRecruitment;
    private String remarks;
}
