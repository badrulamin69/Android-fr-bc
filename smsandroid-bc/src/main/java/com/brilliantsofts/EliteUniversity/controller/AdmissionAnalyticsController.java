package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.repository.AdmissionApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admission-analytics")
@RequiredArgsConstructor
public class AdmissionAnalyticsController {

    private final AdmissionApplicationRepository applicationRepository;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalApplications", applicationRepository.count());
        return ResponseEntity.ok(stats);
    }
}
