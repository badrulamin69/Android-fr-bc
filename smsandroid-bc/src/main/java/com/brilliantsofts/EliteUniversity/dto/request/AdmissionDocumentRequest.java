package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AdmissionDocumentRequest {
    private Long confirmationId;
    private String documentType;
    private String documentName;
    private String fileUrl;
    private Long fileSize;
    private String status;
    private Long verifiedBy;
    private String remarks;
}
