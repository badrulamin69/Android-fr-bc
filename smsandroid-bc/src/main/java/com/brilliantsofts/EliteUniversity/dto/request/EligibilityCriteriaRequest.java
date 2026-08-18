package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class EligibilityCriteriaRequest {
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
}