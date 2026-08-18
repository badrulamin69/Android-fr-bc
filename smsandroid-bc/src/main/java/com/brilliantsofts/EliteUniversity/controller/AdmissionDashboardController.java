package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.repository.AdmissionApplicationRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionTestRepository;
import com.brilliantsofts.EliteUniversity.repository.AdmissionCandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admission-dashboard")
@RequiredArgsConstructor
public class AdmissionDashboardController {
    private final AdmissionApplicationRepository applicationRepository;
    private final AdmissionTestRepository testRepository;
    private final AdmissionCandidateRepository candidateRepository;

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalApplications", applicationRepository.count());
        stats.put("totalTests", testRepository.count());
        stats.put("totalCandidates", candidateRepository.count());
        return ResponseEntity.ok(stats);
    }
}
