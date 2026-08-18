package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlumniRequest {
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
