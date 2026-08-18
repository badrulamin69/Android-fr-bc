package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReportTemplateResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String description;
    private String reportType;
    private String templateConfig;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
