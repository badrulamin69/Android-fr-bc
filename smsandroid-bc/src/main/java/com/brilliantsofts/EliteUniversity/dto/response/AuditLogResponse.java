package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class AuditLogResponse {
    private Long id;
    private String uniqueCode;
    private AuditLogUserResponse user;
    private String action;
    private String entityType;
    private String entityId;
    private String oldValue;
    private String newValue;
    private String ipAddress;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;

    @Data
    public static class AuditLogUserResponse {
        private Long id;
        private String username;
    }
}
