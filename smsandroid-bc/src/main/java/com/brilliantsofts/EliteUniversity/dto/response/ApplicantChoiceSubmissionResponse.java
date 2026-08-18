package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicantChoiceSubmissionResponse {
    private Long id;
    private Long registrationId;
    private Long configId;
    private Long meritListEntryId;
    private String submissionId;
    private Integer totalChoices;
    private String status;
    private LocalDateTime submittedAt;
    private LocalDateTime lockedAt;
    private String applicantName;
    private Integer meritRank;
    private Double meritScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
