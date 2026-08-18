package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AdmissionTestResultRequest {
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
}
