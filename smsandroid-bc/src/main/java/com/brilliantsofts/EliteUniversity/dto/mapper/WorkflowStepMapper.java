package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.WorkflowStepRequest;
import com.brilliantsofts.EliteUniversity.dto.response.WorkflowStepResponse;
import com.brilliantsofts.EliteUniversity.entity.WorkflowStep;

public class WorkflowStepMapper {
    public static WorkflowStep toEntity(WorkflowStepRequest request, Long workflowId) {
        WorkflowStep entity = new WorkflowStep();
        entity.setWorkflowId(workflowId);
        entity.setName(request.getName());
        entity.setStepOrder(request.getStepOrder());
        entity.setRequiredRole(request.getRequiredRole());
        entity.setActive(request.isActive());
        return entity;
    }

    public static WorkflowStepResponse toResponse(WorkflowStep entity) {
        WorkflowStepResponse response = new WorkflowStepResponse();
        response.setId(entity.getId());
        response.setWorkflowId(entity.getWorkflowId());
        response.setName(entity.getName());
        response.setStepOrder(entity.getStepOrder());
        response.setRequiredRole(entity.getRequiredRole());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        return response;
    }
}
