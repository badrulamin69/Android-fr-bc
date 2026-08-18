package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdmissionDocumentResponse {
    private Long id;
    private Long confirmationId;
    private String documentType;
    private String documentName;
    private String fileUrl;
    private Long fileSize;
    private String status;
    private Long verifiedBy;
    private LocalDateTime verifiedAt;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
