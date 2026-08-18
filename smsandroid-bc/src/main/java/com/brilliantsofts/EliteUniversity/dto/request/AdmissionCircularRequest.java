package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdmissionCircularRequest {
    private String uniqueCode;
    private String title;
    private String description;
    private String eligibility;
    private String requiredDocuments;
    private String admissionProcess;
    private LocalDate publishDate;
    private LocalDate validUntil;
    private String status;
    private String attachmentUrl;
    private Boolean isPublished;
    private Long sessionId;
    private Long programId;
}
