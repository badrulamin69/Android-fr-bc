package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class PermissionRequest {
    private String name;
    private String code;
    private String module;
    private String action;
    private String description;
}
