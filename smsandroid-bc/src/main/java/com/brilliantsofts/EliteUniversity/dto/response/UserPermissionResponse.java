package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserPermissionResponse {
    private Long id;
    private String uniqueCode;
    private Long userId;
    private String username;
    private Long permissionId;
    private String permissionName;
    private boolean granted;
    private String notes;
    private Long overriddenById;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
