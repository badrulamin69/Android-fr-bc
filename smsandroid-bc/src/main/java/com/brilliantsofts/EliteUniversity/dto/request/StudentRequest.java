package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentRequest {
    private String studentId;
    private String fullName;
    private String phone;
    private LocalDate admissionDate;
    private Long userId;
    private Long applicantId;
    private Long programId;
    private Long academicSessionId;
}
