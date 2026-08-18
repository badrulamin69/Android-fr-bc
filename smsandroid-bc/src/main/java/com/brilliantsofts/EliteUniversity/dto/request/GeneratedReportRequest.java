package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class GeneratedReportRequest {
    private Long templateId;
    private String title;
    private String reportType;
    private String parameters;
    private String fileUrl;
    private String format;
    private Long generatedBy;
}