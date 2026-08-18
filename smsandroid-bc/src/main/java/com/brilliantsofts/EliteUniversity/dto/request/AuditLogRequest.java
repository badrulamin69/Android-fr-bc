package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AuditLogRequest {
    private Long userId;
    private String action;
    private String entityType;
    private String entityId;
    private String oldValue;
    private String newValue;
    private String ipAddress;
}
