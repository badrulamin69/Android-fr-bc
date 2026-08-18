package com.brilliantsofts.EliteUniversity.dto.request;

import com.brilliantsofts.EliteUniversity.enums.EnrollmentStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EnrollmentRequest {
    private LocalDate enrollmentDate;
    private String semester;
    private EnrollmentStatus status;
    private Long studentId;
    private Long courseId;
}
