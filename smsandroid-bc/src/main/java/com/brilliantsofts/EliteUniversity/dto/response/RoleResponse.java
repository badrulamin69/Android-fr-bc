package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class RoleResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String description;
    private boolean active;
    private List<PermissionResponse> permissions;
    private java.time.LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
