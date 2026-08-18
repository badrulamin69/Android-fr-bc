package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AdmissionWaitingListEntryRequest {
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
}
