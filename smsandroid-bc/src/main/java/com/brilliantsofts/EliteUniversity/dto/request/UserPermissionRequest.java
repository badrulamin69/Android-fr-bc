package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserPermissionRequest {
    private Long userId;
    private Long permissionId;
    private boolean granted;
    private String notes;
    private Long overriddenById;
    private LocalDateTime expiresAt;
}
