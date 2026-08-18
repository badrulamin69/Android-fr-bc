package com.brilliantsofts.EliteUniversity.dto.response;

import com.brilliantsofts.EliteUniversity.enums.AcademicExamType;
import lombok.Data;

@Data
public class AcademicResultResponse {
    private Long id;
    private AcademicExamType examType;
    private String board;
    private String institutionName;
    private String rollNumber;
    private String registrationNumber;
    private Integer passingYear;
    private Double gpa;
    private String resultDocument;
    private Long applicantId;
    private String applicantName;
}
