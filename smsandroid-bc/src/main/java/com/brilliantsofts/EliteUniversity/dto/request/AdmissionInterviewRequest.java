package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionInterviewRequest {
    private String uniqueCode;
    private Long applicationId;
    private Long interviewerId;
    private LocalDateTime scheduledAt;
    private LocalDateTime completedAt;
    private String interviewType;
    private String status;
    private String remarks;
    private Double score;
    private Double maxScore;
    private String strengths;
    private String weaknesses;
    private Boolean isRecommended;
}
