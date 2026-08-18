package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AdmissionRequirementRequest {
    private String name;
    private String description;
    private String requirementType;
    private String applicableTo;
    private Long programId;
    private Long departmentId;
    private Long facultyId;
    private boolean isMandatory;
    private String status;
}
