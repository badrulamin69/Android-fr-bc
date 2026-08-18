package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResultSheetResponse {
    private Long studentDbId;
    private String studentId;
    private String fullName;
    private String programName;
    private String departmentName;
    private String facultyName;
    private String academicSessionName;
    private String semester;
    private List<String> availableSemesters;
    private List<ResultSheetCourseRow> courseRows;
    private Double totalRegisteredCredits;
    private Double totalCompletedCredits;
    private Double totalEarnedCredits;
    private Double semesterGpa;
    private Double previousCgpa;
    private Double currentCgpa;
    private String academicStatus;
}
