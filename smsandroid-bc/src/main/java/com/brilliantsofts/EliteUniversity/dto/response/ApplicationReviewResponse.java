package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationReviewResponse {
    private Long id;
    private String uniqueCode;
    private Long applicationId;
    private Long reviewerId;
    private String status;
    private String comments;
    private String rejectionReason;
    private Double score;
    private LocalDateTime reviewedAt;
    private Boolean isRecommended;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
