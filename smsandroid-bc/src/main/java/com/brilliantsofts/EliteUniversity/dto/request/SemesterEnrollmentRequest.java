package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SemesterEnrollmentRequest {
    private Long studentId;
    private Long semesterId;
    private Long batchId;
    private Long programId;
    private Long facultyId;
    private Long departmentId;
    private Long advisorId;
    private String status;
    private Integer registeredCredits;
    private Integer minCredits;
    private Integer maxCredits;
    private String remarks;
    private String enrollmentType;
}
