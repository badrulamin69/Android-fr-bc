package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ResultRequest {
    private Long examId;
    private Long studentId;
    private BigDecimal totalMarksObtained;
    private BigDecimal totalMarks;
    private BigDecimal percentage;
    private String grade;
    private String resultStatus;
    private String remarks;
}
