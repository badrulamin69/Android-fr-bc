package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDocumentResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private String documentType;
    private String documentName;
    private String fileUrl;
    private Long fileSize;
    private LocalDateTime uploadedAt;
    private String status;
    private Long verifiedById;
    private LocalDateTime verifiedAt;
    private String remarks;
}
