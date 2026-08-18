package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AdmissionCircularResponse {
    private Long id;
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
