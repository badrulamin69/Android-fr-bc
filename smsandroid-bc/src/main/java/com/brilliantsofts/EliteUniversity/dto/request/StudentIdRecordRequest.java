package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class StudentIdRecordRequest {
    private Long studentId;
    private String studentCode;
    private String idNumber;
    private String idType;
    private String status;
    private String issuedBy;
    private String remarks;
}
