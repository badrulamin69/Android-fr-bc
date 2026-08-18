package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicantTestResultView {
    private Long applicantId;
    private String applicantRoll;
    private String applicantName;
    private Long resultId;
    private Long registrationId;
    private Double writtenMarks;
    private Double mcqMarks;
    private Double vivaMarks;
    private Double writtenMax;
    private Double mcqMax;
    private Double vivaMax;
    private Double totalWeightedScore;
    private String status;
    private String remarks;
    private Long testId;
}
