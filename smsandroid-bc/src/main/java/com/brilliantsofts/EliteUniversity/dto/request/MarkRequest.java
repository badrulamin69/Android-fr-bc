package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MarkRequest {
    private Long examId;
    private Long studentId;
    private Long subjectId;
    private BigDecimal marksObtained;
    private BigDecimal totalMarks;
    private String grade;
    private String remarks;
}
