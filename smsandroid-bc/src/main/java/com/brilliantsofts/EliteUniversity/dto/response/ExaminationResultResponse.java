package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class ExaminationResultResponse {
    private Long id;
    private Double marks;
    private Double gradePoint;
    private String grade;
    private Double credit;
    private Long studentId;
    private String studentName;
    private Long examinationId;
    private String examinationName;
}
