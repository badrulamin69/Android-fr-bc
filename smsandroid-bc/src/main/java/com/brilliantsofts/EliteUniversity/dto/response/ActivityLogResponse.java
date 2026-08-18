package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class ActivityLogResponse {
    private Long id;
    private String uniqueCode;
    private Long userId;
    private String username;
    private String action;
    private String module;
    private String description;
    private String entityType;
    private String entityId;
    private String ipAddress;
    private String userAgent;
    private String metadata;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
