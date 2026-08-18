package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StudentIdRecordResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private String studentCode;
    private String idNumber;
    private String idType;
    private String status;
    private LocalDateTime issuedAt;
    private String issuedBy;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
