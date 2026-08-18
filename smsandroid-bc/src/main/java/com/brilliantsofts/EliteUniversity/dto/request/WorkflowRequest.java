package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class WorkflowRequest {
    private String name;
    private String description;
    private String entityType;
    private boolean isActive;
}
