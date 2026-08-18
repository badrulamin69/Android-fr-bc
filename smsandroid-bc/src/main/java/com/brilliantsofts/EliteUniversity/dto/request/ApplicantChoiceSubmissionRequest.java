package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ApplicantChoiceSubmissionRequest {
    private Long registrationId;
    private Long configId;
    private Long meritListEntryId;
    private String submissionId;
    private Integer totalChoices;
    private String status;
    private String applicantName;
    private Integer meritRank;
    private Double meritScore;
}
