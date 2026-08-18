package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionTestResultResponse {
    private Long id;
    private Double writtenMarks;
    private Double mcqMarks;
    private Double vivaMarks;
    private Double writtenMax;
    private Double mcqMax;
    private Double vivaMax;
    private Double totalWeightedScore;
    private String status;
    private String remarks;
    private Long registrationId;
    private Long testId;
    private String applicantRoll;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
