package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionEnrollmentResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admission-enrollments")
@RequiredArgsConstructor
public class AdmissionEnrollmentController {

    private final AdmissionEnrollmentService service;

    @PostMapping
    public ResponseEntity<AdmissionEnrollmentResponse> create(@RequestBody AdmissionEnrollmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionEnrollmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionEnrollmentResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy, sortDir, search));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionEnrollmentResponse> update(@PathVariable Long id, @RequestBody AdmissionEnrollmentRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
