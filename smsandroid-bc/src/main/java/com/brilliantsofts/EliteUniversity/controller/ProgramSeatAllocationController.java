package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramSeatAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramSeatAllocationResponse;
import com.brilliantsofts.EliteUniversity.service.ProgramSeatAllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/program-seat-allocations")
@RequiredArgsConstructor
public class ProgramSeatAllocationController {

    private final ProgramSeatAllocationService service;

    @GetMapping("/admin/allocations")
    public ResponseEntity<Page<ProgramSeatAllocationResponse>> getAll(
            @RequestParam(required = false) Long configId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(search, configId, pageable));
    }

    @GetMapping("/admin/allocations/{id}")
    public ResponseEntity<ProgramSeatAllocationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/admin/stats/{configId}")
    public ResponseEntity<Map<String, Object>> getStats(@PathVariable Long configId) {
        return ResponseEntity.ok(service.getStats(configId));
    }

    @PostMapping("/admin/auto-allocate/{configId}")
    public ResponseEntity<Map<String, Object>> runAutoAllocation(@PathVariable Long configId) {
        return ResponseEntity.ok(service.runAutoAllocation(configId));
    }

    @PostMapping("/admin/manual-allocate")
    public ResponseEntity<ProgramSeatAllocationResponse> manualAllocate(
            @RequestParam Long registrationId, @RequestParam Long programId,
            @RequestParam Long configId, @RequestParam String shift,
            @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(service.manualAllocate(registrationId, programId, configId, shift, remarks));
    }

    @PutMapping("/admin/change-allocation/{id}")
    public ResponseEntity<ProgramSeatAllocationResponse> changeAllocation(
            @PathVariable Long id, @RequestParam Long newProgramId,
            @RequestParam String shift, @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(service.changeAllocation(id, newProgramId, shift, remarks));
    }

    @PutMapping("/admin/cancel/{id}")
    public ResponseEntity<ProgramSeatAllocationResponse> cancelAllocation(
            @PathVariable Long id, @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(service.cancelAllocation(id, remarks));
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<ProgramSeatAllocationResponse> acceptAllocation(@PathVariable Long id) {
        return ResponseEntity.ok(service.acceptAllocation(id));
    }

    @PostMapping("/decline/{id}")
    public ResponseEntity<ProgramSeatAllocationResponse> declineAllocation(
            @PathVariable Long id, @RequestParam(required = false) String remarks) {
        return ResponseEntity.ok(service.declineAllocation(id, remarks));
    }

    @PostMapping("/admin/reallocate/{configId}")
    public ResponseEntity<Map<String, Object>> reallocate(@PathVariable Long configId) {
        return ResponseEntity.ok(service.runAutoAllocation(configId));
    }

    @PostMapping("/admin/expire-overdue/{configId}")
    public ResponseEntity<Map<String, Object>> expireOverdue(@PathVariable Long configId) {
        Map<String, Object> res = new java.util.HashMap<>();
        res.put("expiredCount", 0);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/admin/demand-report/{configId}")
    public ResponseEntity<java.util.List<Map<String, Object>>> getDemandReport(@PathVariable Long configId) {
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }

    @GetMapping("/my-allocation")
    public ResponseEntity<ProgramSeatAllocationResponse> getMyAllocation() {
        return ResponseEntity.ok(null);
    }
}
