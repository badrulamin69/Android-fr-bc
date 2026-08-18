package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class StudentDocumentRequest {
    private Long studentId;
    private String documentType;
    private String documentName;
    private String fileUrl;
    private Long fileSize;
    private String status;
    private Long verifiedById;
    private String remarks;
}
