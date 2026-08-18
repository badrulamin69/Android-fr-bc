package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class PermissionResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String module;
    private String action;
    private String description;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
