package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AdmissionCampaignResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String type;
    private String description;
    private Double budget;
    private Double spent;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String targetAudience;
    private String channels;
    private Integer applicationsGenerated;
    private Integer enrollmentsConverted;
    private String notes;
    private Long sessionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
