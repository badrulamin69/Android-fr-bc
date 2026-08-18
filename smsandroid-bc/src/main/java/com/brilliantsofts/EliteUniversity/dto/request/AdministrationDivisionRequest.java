package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AdministrationDivisionRequest {
    private String name;
    private String code;
    private String description;
    private String deanName;
    private Long campusId;
    private Boolean isActive;
}
