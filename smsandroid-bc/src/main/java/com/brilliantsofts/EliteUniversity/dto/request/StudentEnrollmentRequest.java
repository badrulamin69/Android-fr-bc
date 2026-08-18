package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentEnrollmentRequest {
    private Long studentId;
    private Long batchId;
    private Long sectionId;
    private LocalDate enrollmentDate;
    private String status;
}
