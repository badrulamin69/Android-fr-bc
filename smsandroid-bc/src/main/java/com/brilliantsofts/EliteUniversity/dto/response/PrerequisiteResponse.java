package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PrerequisiteResponse {
    private Long id;
    private String uniqueCode;
    private Long subjectId;
    private Long prerequisiteSubjectId;
    private String minGrade;
    private boolean isMandatory;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
