package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdmissionRequirementResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String description;
    private String requirementType;
    private String applicableTo;
    private Long programId;
    private Long departmentId;
    private Long facultyId;
    private boolean isMandatory;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
