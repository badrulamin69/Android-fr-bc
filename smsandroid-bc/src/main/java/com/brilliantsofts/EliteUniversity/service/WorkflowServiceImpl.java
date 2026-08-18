package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.WorkflowMapper;
import com.brilliantsofts.EliteUniversity.dto.mapper.WorkflowStepMapper;
import com.brilliantsofts.EliteUniversity.dto.request.WorkflowRequest;
import com.brilliantsofts.EliteUniversity.dto.request.WorkflowStepRequest;
import com.brilliantsofts.EliteUniversity.dto.response.WorkflowResponse;
import com.brilliantsofts.EliteUniversity.dto.response.WorkflowStepResponse;
import com.brilliantsofts.EliteUniversity.entity.Workflow;
import com.brilliantsofts.EliteUniversity.entity.WorkflowStep;
import com.brilliantsofts.EliteUniversity.repository.WorkflowRepository;
import com.brilliantsofts.EliteUniversity.repository.WorkflowStepRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService {

    private final WorkflowRepository repository;
    private final WorkflowStepRepository stepRepository;

    @Override
    public WorkflowResponse create(WorkflowRequest request) {
        Workflow entity = WorkflowMapper.toEntity(request);
        WorkflowResponse response = WorkflowMapper.toResponse(repository.save(entity));
        response.setSteps(List.of());
        return response;
    }

    @Override
    public WorkflowResponse update(Long id, WorkflowRequest request) {
        Workflow entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setEntityType(request.getEntityType());
        entity.setActive(request.isActive());
        return WorkflowMapper.toResponse(repository.save(entity));
    }

    @Override
    public WorkflowResponse getById(Long id) {
        Workflow entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found with id: " + id));
        WorkflowResponse response = WorkflowMapper.toResponse(entity);
        List<WorkflowStepResponse> steps = stepRepository.findByWorkflowIdOrderByStepOrder(id)
                .stream().map(WorkflowStepMapper::toResponse).collect(Collectors.toList());
        response.setSteps(steps);
        return response;
    }

    @Override
    public Page<WorkflowResponse> getAll(String search, Pageable pageable) {
        return repository.findAllWithSearch(search, pageable)
                .map(WorkflowMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Workflow not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public WorkflowStepResponse addStep(Long workflowId, WorkflowStepRequest request) {
        WorkflowStep entity = WorkflowStepMapper.toEntity(request, workflowId);
        return WorkflowStepMapper.toResponse(stepRepository.save(entity));
    }

    @Override
    public WorkflowStepResponse updateStep(Long stepId, WorkflowStepRequest request) {
        WorkflowStep entity = stepRepository.findById(stepId)
                .orElseThrow(() -> new RuntimeException("WorkflowStep not found with id: " + stepId));
        entity.setName(request.getName());
        entity.setStepOrder(request.getStepOrder());
        entity.setRequiredRole(request.getRequiredRole());
        entity.setActive(request.isActive());
        return WorkflowStepMapper.toResponse(stepRepository.save(entity));
    }

    @Override
    public void deleteStep(Long stepId) {
        if (!stepRepository.existsById(stepId)) {
            throw new RuntimeException("WorkflowStep not found with id: " + stepId);
        }
        stepRepository.deleteById(stepId);
    }

    @Override
    public List<WorkflowStepResponse> getSteps(Long workflowId) {
        return stepRepository.findByWorkflowIdOrderByStepOrder(workflowId)
                .stream().map(WorkflowStepMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public Page<java.util.Map<String, Object>> getApprovals(String entityType, String entityId, Pageable pageable) {
        return Page.empty(pageable);
    }

    @Override
    public void approveLeaveRequest(Long id) {
        // Handle leave request approval logic here
    }

    @Override
    public void rejectLeaveRequest(Long id) {
        // Handle leave request rejection logic here
    }
}
