package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentResponse {
    private Long id;
    private String studentId;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate admissionDate;
    private Long userId;
    private Long applicantId;
    private Long programId;
    private String programName;
    private String departmentName;
    private Long academicSessionId;
    private String sessionName;

    // Compatibility aliases
    private String uniqueCode;
    private String firstName;
    private String lastName;
    private String studentCode;
    private String status;
}
