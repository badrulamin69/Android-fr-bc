package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.service.AcademicDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/academic-dashboard")
@RequiredArgsConstructor
public class AcademicDashboardController {
    private final AcademicDashboardService service;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }
}
