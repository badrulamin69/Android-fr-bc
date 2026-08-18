package com.brilliantsofts.EliteUniversity.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamRequest {
    private String name;
    private String examType;
    private Long courseId;
    private Long subjectId;
    private Integer totalMarks;
    private Integer passingMarks;
    
    @JsonFormat(pattern = "yyyy-MM-dd[ HH:mm[:ss]][['T']HH:mm[:ss]]")
    private LocalDateTime examDate;
    
    private Integer durationMinutes;
    private String description;
}
