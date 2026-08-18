package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EligibilityCriteriaResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String description;
    private String criteriaType;
    private Double minValue;
    private Double maxValue;
    private String applicableTo;
    private Long programId;
    private Long departmentId;
    private Long facultyId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}