package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentAttendanceResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private Long courseId;
    private Long semesterId;
    private LocalDate attendanceDate;
    private String status;
    private String remarks;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private Long recordedById;

    // Enriched relational attributes
    private String studentName;
    private String studentCode;
    private String courseName;
    private String courseCode;
    private String semesterName;
    private String departmentName;
}
