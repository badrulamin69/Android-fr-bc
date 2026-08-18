package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdvisorApprovalRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdvisorApprovalResponse;
import com.brilliantsofts.EliteUniversity.service.AdvisorApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advisor-approvals")
@RequiredArgsConstructor
public class AdvisorApprovalController {
    private final AdvisorApprovalService service;

    @GetMapping("/pending/{semesterId}")
    public ResponseEntity<List<AdvisorApprovalResponse>> getPendingApprovals(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getPendingApprovals(semesterId));
    }

    @PostMapping("/process")
    public ResponseEntity<AdvisorApprovalResponse> processApproval(@RequestBody AdvisorApprovalRequest request) {
        return ResponseEntity.ok(service.processApproval(request));
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<AdvisorApprovalResponse>> processBulkApproval(
            @RequestBody List<Long> studentIds,
            @RequestParam Long semesterId,
            @RequestParam String action,
            @RequestParam(required = false) String comments) {
        return ResponseEntity.ok(service.processBulkApproval(studentIds, semesterId, action, comments));
    }
}
