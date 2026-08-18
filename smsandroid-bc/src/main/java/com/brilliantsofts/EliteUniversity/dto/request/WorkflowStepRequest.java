package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class WorkflowStepRequest {
    private String name;
    private Integer stepOrder;
    private String requiredRole;
    private boolean isActive;
}
