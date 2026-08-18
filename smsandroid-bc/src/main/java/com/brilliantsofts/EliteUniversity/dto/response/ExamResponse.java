package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String examType;
    private Long courseId;
    private String courseName;
    private Long subjectId;
    private String subjectName;
    private Integer totalMarks;
    private Integer passingMarks;
    private LocalDateTime examDate;
    private Integer durationMinutes;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
