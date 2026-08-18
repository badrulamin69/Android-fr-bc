package com.brilliantsofts.EliteUniversity.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AssignmentRequest {
    private String title;
    private String description;
    
    @JsonFormat(pattern = "yyyy-MM-dd[ HH:mm[:ss]][['T']HH:mm[:ss]]")
    private LocalDateTime dueDate;
    
    private Integer maxMarks;
    private Long courseId;
    private Long subjectId;
    private Long sectionId;
    private Long administrationId;
}
