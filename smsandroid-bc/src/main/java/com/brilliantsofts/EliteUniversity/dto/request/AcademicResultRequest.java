package com.brilliantsofts.EliteUniversity.dto.request;

import com.brilliantsofts.EliteUniversity.enums.AcademicExamType;
import lombok.Data;

@Data
public class AcademicResultRequest {
    private AcademicExamType examType;
    private String board;
    private String institutionName;
    private String rollNumber;
    private String registrationNumber;
    private Integer passingYear;
    private Double gpa;
    private String resultDocument;
    private Long applicantId;
}
