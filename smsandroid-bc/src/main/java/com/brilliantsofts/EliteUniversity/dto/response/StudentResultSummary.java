package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class StudentResultSummary {
    private Long studentDbId;
    private String studentId;
    private String fullName;
    private String programName;
    private String departmentName;
    private String facultyName;
    private String academicSessionName;
    private String currentSemester;
    private Double semesterGpa;
    private Double cgpa;
    private String status;
}
