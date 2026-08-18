package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.service.SecurityDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/security/dashboard")
@RequiredArgsConstructor
public class SecurityDashboardController {
    private final SecurityDashboardService service;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }
    @GetMapping("/login-stats")
    public ResponseEntity<Map<String, Object>> getLoginStats() {
        return ResponseEntity.ok(service.getLoginStats());
    }

    @GetMapping("/recent-activities")
    public ResponseEntity<java.util.List<Map<String, Object>>> getRecentActivities() {
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }
}
