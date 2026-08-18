package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicantChoiceResponse {
    private Long id;
    private Long submissionId;
    private Integer priority;
    private Long facultyId;
    private Long departmentId;
    private Long programId;
    private String facultyName;
    private String departmentName;
    private String programName;
    private String shift;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
