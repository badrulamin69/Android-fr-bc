package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionWaitingListEntryResponse {
    private Long id;
    private Long waitingListId;
    private Long registrationId;
    private Integer rank;
    private String rollNumber;
    private String applicationNumber;
    private String applicantName;
    private Double score;
    private Double testMarks;
    private Double totalWeightedScore;
    private String status;
    private Boolean isPromoted;
    private Boolean isOffered;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
