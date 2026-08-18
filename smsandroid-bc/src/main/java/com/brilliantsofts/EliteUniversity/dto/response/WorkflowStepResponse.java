package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WorkflowStepResponse {
    private Long id;
    private Long workflowId;
    private String name;
    private Integer stepOrder;
    private String requiredRole;
    private boolean isActive;
    private LocalDateTime createdAt;
}
