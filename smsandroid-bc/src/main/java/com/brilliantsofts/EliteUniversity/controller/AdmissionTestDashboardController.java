package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.repository.AdmissionTestRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admission-test-dashboard")
@RequiredArgsConstructor
public class AdmissionTestDashboardController {
    private final AdmissionTestRepository testRepository;
    private final AdmissionTestResultRepository testResultRepository;

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalTests", testRepository.count());
        stats.put("totalResults", testResultRepository.count());
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/charts")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Map<String, Object>> getCharts() {
        Map<String, Object> charts = new HashMap<>();
        charts.put("totalTests", testRepository.count());
        charts.put("totalResults", testResultRepository.count());
        return ResponseEntity.ok(charts);
    }
}
