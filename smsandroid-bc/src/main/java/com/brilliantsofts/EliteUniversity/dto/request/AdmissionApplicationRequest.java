package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionApplicationRequest {
    private String uniqueCode;
    private String applicationNumber;
    private Long candidateId;
    private Long circularId;
    private Long sessionId;
    private Long programId;
    private Long departmentId;
    private Long campusId;
    private String status;
    private String remarks;
    private LocalDateTime submittedAt;
    private Boolean isSubmitted;
    private Boolean isVerified;
    private Long examId;
    private Double testScore;
    private Double meritScore;
    private Integer meritPosition;
}
