package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.WorkflowRequest;
import com.brilliantsofts.EliteUniversity.dto.request.WorkflowStepRequest;
import com.brilliantsofts.EliteUniversity.dto.response.WorkflowResponse;
import com.brilliantsofts.EliteUniversity.dto.response.WorkflowStepResponse;
import com.brilliantsofts.EliteUniversity.service.WorkflowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workflows")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowService service;

    @PostMapping
    public ResponseEntity<WorkflowResponse> create(@RequestBody WorkflowRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkflowResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<WorkflowResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAll(search, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkflowResponse> update(@PathVariable Long id, @RequestBody WorkflowRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{workflowId}/steps")
    public ResponseEntity<WorkflowStepResponse> addStep(@PathVariable Long workflowId, @RequestBody WorkflowStepRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addStep(workflowId, request));
    }

    @PutMapping("/steps/{stepId}")
    public ResponseEntity<WorkflowStepResponse> updateStep(@PathVariable Long stepId, @RequestBody WorkflowStepRequest request) {
        return ResponseEntity.ok(service.updateStep(stepId, request));
    }

    @DeleteMapping("/steps/{stepId}")
    public ResponseEntity<Void> deleteStep(@PathVariable Long stepId) {
        service.deleteStep(stepId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{workflowId}/steps")
    public ResponseEntity<java.util.List<WorkflowStepResponse>> getSteps(@PathVariable Long workflowId) {
        return ResponseEntity.ok(service.getSteps(workflowId));
    }

    @GetMapping("/approvals")
    public ResponseEntity<Page<java.util.Map<String, Object>>> getApprovals(
            @RequestParam(required = false) String entityType,
            @RequestParam(required = false) String entityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getApprovals(entityType, entityId, pageable));
    }

    @PostMapping("/leave-requests/{id}/approve")
    public ResponseEntity<Void> approveLeaveRequest(@PathVariable Long id) {
        service.approveLeaveRequest(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/leave-requests/{id}/reject")
    public ResponseEntity<Void> rejectLeaveRequest(@PathVariable Long id) {
        service.rejectLeaveRequest(id);
        return ResponseEntity.ok().build();
    }
}
