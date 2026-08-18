package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CourseRegistrationResponse {
    private Long id;
    private String uniqueCode;
    private Long studentId;
    private String studentName;
    private String studentCode;
    private Long courseId;
    private String courseName;
    private String courseCode;
    private Long semesterId;
    private String semesterName;
    private Long batchId;
    private String batchName;
    private String status;
    private LocalDateTime registrationDate;
    private boolean selected;
    private Integer creditHours;
    private String remarks;
    private Long approvedById;
    private String approvedByName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
