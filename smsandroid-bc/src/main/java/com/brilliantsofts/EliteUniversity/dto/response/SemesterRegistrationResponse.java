package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SemesterRegistrationResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private Long semesterId;
    private String semesterName;
    private Long batchId;
    private String batchName;
    private LocalDateTime registrationDate;
    private String status;
    private String remarks;
    private Long approvedById;
    private String approvedByName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
