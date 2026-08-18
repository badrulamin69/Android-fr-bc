package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class WorkflowResponse {
    private Long id;
    private String name;
    private String description;
    private String entityType;
    private boolean isActive;
    private List<WorkflowStepResponse> steps;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
