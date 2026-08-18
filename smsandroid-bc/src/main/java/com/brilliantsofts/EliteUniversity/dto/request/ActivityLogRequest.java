package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class ActivityLogRequest {
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
}
