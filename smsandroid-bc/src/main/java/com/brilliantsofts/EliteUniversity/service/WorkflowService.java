package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.WorkflowRequest;
import com.brilliantsofts.EliteUniversity.dto.request.WorkflowStepRequest;
import com.brilliantsofts.EliteUniversity.dto.response.WorkflowResponse;
import com.brilliantsofts.EliteUniversity.dto.response.WorkflowStepResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkflowService {
    WorkflowResponse create(WorkflowRequest request);
    WorkflowResponse update(Long id, WorkflowRequest request);
    WorkflowResponse getById(Long id);
    Page<WorkflowResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
    WorkflowStepResponse addStep(Long workflowId, WorkflowStepRequest request);
    WorkflowStepResponse updateStep(Long stepId, WorkflowStepRequest request);
    void deleteStep(Long stepId);

    java.util.List<WorkflowStepResponse> getSteps(Long workflowId);
    Page<java.util.Map<String, Object>> getApprovals(String entityType, String entityId, Pageable pageable);
    void approveLeaveRequest(Long id);
    void rejectLeaveRequest(Long id);
}
