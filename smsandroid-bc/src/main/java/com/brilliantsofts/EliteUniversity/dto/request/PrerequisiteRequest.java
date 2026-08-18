package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class PrerequisiteRequest {
    private Long subjectId;
    private Long prerequisiteSubjectId;
    private String minGrade;
    private boolean isMandatory;
}
