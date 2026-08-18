package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ReportTemplateRequest {
    private String name;
    private String code;
    private String description;
    private String reportType;
    private String templateConfig;
    private boolean isActive;
}
