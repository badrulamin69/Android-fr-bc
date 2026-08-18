package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionAttendanceRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionAttendanceResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionAttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admission-attendance")
@RequiredArgsConstructor
public class AdmissionAttendanceController {

    private final AdmissionAttendanceService service;

    @PostMapping
    public ResponseEntity<AdmissionAttendanceResponse> create(@RequestBody AdmissionAttendanceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionAttendanceResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionAttendanceResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy, sortDir, search));
    }

    @GetMapping("/test/{testId}")
    public ResponseEntity<List<AdmissionAttendanceResponse>> findByTestId(@PathVariable Long testId) {
        return ResponseEntity.ok(service.findByTestId(testId));
    }

    @GetMapping("/stats/{testId}")
    public ResponseEntity<Map<String, Object>> getStatsByTestId(@PathVariable Long testId) {
        return ResponseEntity.ok(service.getStatsByTestId(testId));
    }

    @PostMapping("/mark")
    public ResponseEntity<AdmissionAttendanceResponse> markAttendance(@RequestBody AdmissionAttendanceRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.markAttendance(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionAttendanceResponse> update(@PathVariable Long id, @RequestBody AdmissionAttendanceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
