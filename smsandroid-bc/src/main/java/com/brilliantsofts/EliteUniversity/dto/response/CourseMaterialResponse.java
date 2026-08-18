package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CourseMaterialResponse {
    private Long id;
    private String uniqueCode;
    private String title;
    private String description;
    private String materialType;
    private String fileUrl;
    private Long courseId;
    private String courseName;
    private Long subjectId;
    private String subjectName;
    private Long administrationId;
    private String administrationName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
