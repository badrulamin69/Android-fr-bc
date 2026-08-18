package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class TranscriptRequest {
    private String transcriptNumber;
    private Long studentId;
    private Long programId;
    private Long semesterId;
    private String status;
    private Double gpa;
    private Integer totalCredits;
    private String remarks;
    private Long issuedById;
}
