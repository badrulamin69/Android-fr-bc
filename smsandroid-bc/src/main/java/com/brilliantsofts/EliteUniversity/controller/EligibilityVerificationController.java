package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.EligibilityVerificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EligibilityVerificationResponse;
import com.brilliantsofts.EliteUniversity.service.EligibilityVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/eligibility-verifications")
@RequiredArgsConstructor
public class EligibilityVerificationController {

    private final EligibilityVerificationService service;

    @PostMapping("/verify")
    public ResponseEntity<EligibilityVerificationResponse> verify(@RequestBody EligibilityVerificationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.verify(request));
    }

    @PostMapping("/auto-verify/{testId}")
    public ResponseEntity<List<EligibilityVerificationResponse>> autoVerifyAll(@PathVariable Long testId) {
        return ResponseEntity.ok(service.autoVerifyAll(testId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EligibilityVerificationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<EligibilityVerificationResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long testId) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(search, testId, pageable));
    }

    @GetMapping("/test/{testId}")
    public ResponseEntity<List<EligibilityVerificationResponse>> getByTestId(@PathVariable Long testId) {
        return ResponseEntity.ok(service.getByTestId(testId));
    }

    @GetMapping("/stats/{testId}")
    public ResponseEntity<Map<String, Object>> getStats(@PathVariable Long testId) {
        return ResponseEntity.ok(service.getStats(testId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}