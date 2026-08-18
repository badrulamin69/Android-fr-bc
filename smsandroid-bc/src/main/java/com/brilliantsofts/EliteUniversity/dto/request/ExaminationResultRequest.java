package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ExaminationResultRequest {
    private Double marks;
    private Double gradePoint;
    private String grade;
    private Double credit;
    private Long studentId;
    private Long examinationId;
}
