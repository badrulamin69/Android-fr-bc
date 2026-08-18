package com.brilliantsofts.EliteUniversity.dto.response;

import com.brilliantsofts.EliteUniversity.enums.EnrollmentStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EnrollmentResponse {
    private Long id;
    private LocalDate enrollmentDate;
    private String semester;
    private EnrollmentStatus status;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
}
