package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionMeritListRequest {
    private String name;
    private String description;
    private String academicYear;
    private Long sessionId;
    private Long facultyId;
    private Long departmentId;
    private Long programId;
    private String shift;
    private String quotaType;
    private Long testId;
    private Long circularId;
    private String status;
    private Integer totalSeats;
    private Integer totalApplicants;
    private Integer selectedCount;
    private Integer waitingCount;
    private Double cutoffScore;
    private LocalDateTime publishedAt;
    private String publishedBy;
    private String remarks;
}
