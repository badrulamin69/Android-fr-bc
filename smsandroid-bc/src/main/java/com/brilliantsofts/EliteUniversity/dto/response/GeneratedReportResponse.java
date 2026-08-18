package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GeneratedReportResponse {
    private Long id;
    private String uniqueCode;
    private Long templateId;
    private String title;
    private String reportType;
    private String parameters;
    private String fileUrl;
    private String format;
    private Long generatedBy;
    private LocalDateTime generatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}