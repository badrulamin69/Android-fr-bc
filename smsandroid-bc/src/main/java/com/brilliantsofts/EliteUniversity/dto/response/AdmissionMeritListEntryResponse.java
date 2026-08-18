package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionMeritListEntryResponse {
    private Long id;
    private Long meritListId;
    private Long registrationId;
    private Integer rank;
    private String rollNumber;
    private String applicationNumber;
    private String applicantName;
    private String facultyName;
    private String departmentName;
    private String programName;
    private String shift;
    private Double testMarks;
    private Double testMaxMarks;
    private Double score;
    private Double academicScore;
    private Double totalWeightedScore;
    private Double sscGpa;
    private Double hscGpa;
    private String quotaType;
    private String status;
    private Boolean isOffered;
    private Boolean isEnrolled;
    private String remarks;
    private LocalDateTime submittedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
