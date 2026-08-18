package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class DocumentSubmitRequest {
    private String documentType;
    private String documentName;
    private String fileUrl;
}
