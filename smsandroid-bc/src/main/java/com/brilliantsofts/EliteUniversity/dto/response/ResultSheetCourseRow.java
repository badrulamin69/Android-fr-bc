package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class ResultSheetCourseRow {
    private String courseCode;
    private String courseTitle;
    private Double credit;
    private Double marksObtained;
    private Double totalMarks;
    private String letterGrade;
    private Double gradePoint;
    private Double creditXGradePoint;
}
