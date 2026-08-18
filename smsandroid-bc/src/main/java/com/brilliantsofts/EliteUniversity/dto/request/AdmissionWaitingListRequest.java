package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionWaitingListRequest {
    private String name;
    private String description;
    private String academicYear;
    private Long sessionId;
    private Long facultyId;
    private Long departmentId;
    private Long programId;
    private String shift;
    private Long testId;
    private String status;
    private Integer totalSlots;
    private Integer totalApplicants;
    private Double cutoffScore;
    private LocalDateTime publishedAt;
    private String remarks;
}
