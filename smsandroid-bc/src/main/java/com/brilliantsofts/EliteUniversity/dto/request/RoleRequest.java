package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RoleRequest {
    private String name;
    private String code;
    private String description;
    private boolean active;
    private List<Long> permissionIds;
}
