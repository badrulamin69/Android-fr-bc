package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionApplicationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionApplicationResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admission-applications")
@RequiredArgsConstructor
public class AdmissionApplicationController {
    private final AdmissionApplicationService service;

    @PostMapping
    public ResponseEntity<AdmissionApplicationResponse> create(@RequestBody AdmissionApplicationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionApplicationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionApplicationResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction) {
        String actualSortBy = sortBy;
        String actualSortDir = sortDir;
        if (sort != null && !sort.isEmpty()) {
            actualSortBy = sort;
        }
        if (direction != null && !direction.isEmpty()) {
            actualSortDir = direction;
        }
        Sort sortObj = actualSortDir.equalsIgnoreCase("desc") ? Sort.by(actualSortBy).descending() : Sort.by(actualSortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionApplicationResponse> update(@PathVariable Long id, @RequestBody AdmissionApplicationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unverified")
    public ResponseEntity<Page<AdmissionApplicationResponse>> getUnverified(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getUnverified(pageable));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    @GetMapping("/analytics/monthly-trend")
    public ResponseEntity<java.util.List<Map<String, Object>>> getMonthlyTrend() {
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }

    @GetMapping("/analytics/program-breakdown")
    public ResponseEntity<java.util.List<Map<String, Object>>> getProgramBreakdown() {
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }
}
