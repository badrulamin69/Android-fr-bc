package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class CourseMaterialRequest {
    private String title;
    private String description;
    private String materialType;
    private String fileUrl;
    private Long courseId;
    private Long subjectId;
    private Long administrationId;
}
