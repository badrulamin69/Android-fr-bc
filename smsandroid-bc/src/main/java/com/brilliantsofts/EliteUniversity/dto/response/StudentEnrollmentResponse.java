package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentEnrollmentResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private Long batchId;
    private Long sectionId;
    private LocalDate enrollmentDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
