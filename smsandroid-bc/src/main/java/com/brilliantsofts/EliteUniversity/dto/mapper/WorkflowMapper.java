package com.brilliantsofts.EliteUniversity.dto.mapper;

import com.brilliantsofts.EliteUniversity.dto.request.WorkflowRequest;
import com.brilliantsofts.EliteUniversity.dto.response.WorkflowResponse;
import com.brilliantsofts.EliteUniversity.entity.Workflow;

public class WorkflowMapper {
    public static Workflow toEntity(WorkflowRequest request) {
        Workflow entity = new Workflow();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setEntityType(request.getEntityType());
        entity.setActive(request.isActive());
        return entity;
    }

    public static WorkflowResponse toResponse(Workflow entity) {
        WorkflowResponse response = new WorkflowResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        response.setEntityType(entity.getEntityType());
        response.setActive(entity.isActive());
        response.setCreatedAt(entity.getCreatedAt());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }
}
