package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MarkResponse {
    private Long id;
    private String uniqueCode;
    private Long examId;
    private String examName;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private Long subjectId;
    private String subjectName;
    private BigDecimal marksObtained;
    private BigDecimal totalMarks;
    private String grade;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
