package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TranscriptResponse {
    private Long id;
    private String uniqueCode;
    private String transcriptNumber;
    private Long studentId;
    private Long programId;
    private Long semesterId;
    private LocalDateTime issuedAt;
    private String status;
    private Double gpa;
    private Integer totalCredits;
    private String remarks;
    private Long issuedById;
}
