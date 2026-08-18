package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RolePermissionResponse {
    private Long id;
    private String uniqueCode;
    private Long roleId;
    private String roleName;
    private Long permissionId;
    private String permissionName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
