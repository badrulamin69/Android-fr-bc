package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdministrationDivisionResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String description;
    private String deanName;
    private Long campusId;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
