package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResultResponse {
    private Long id;
    private String uniqueCode;
    private Long examId;
    private String examName;
    private Long courseId;
    private String courseName;
    private String courseCode;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private BigDecimal totalMarksObtained;
    private BigDecimal marksObtained;
    private BigDecimal totalMarks;
    private BigDecimal percentage;
    private String grade;
    private BigDecimal gpa;
    private String resultStatus;
    private String remarks;
    private String programName;
    private String departmentName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
