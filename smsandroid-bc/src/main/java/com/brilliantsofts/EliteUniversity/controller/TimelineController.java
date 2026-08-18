package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.response.TimelineEventResponse;
import com.brilliantsofts.EliteUniversity.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/timeline")
@RequiredArgsConstructor
public class TimelineController {

    private final TimelineService service;

    @GetMapping
    public ResponseEntity<Page<TimelineEventResponse>> getTimeline(
            @RequestParam String entityType,
            @RequestParam Long entityId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getByEntityTypeAndEntityId(entityType, entityId, pageable));
    }

    @GetMapping("/recent")
    public ResponseEntity<Page<TimelineEventResponse>> getRecentTimeline(
            @RequestParam String entityType,
            @RequestParam Long entityId) {
        Pageable pageable = PageRequest.of(0, 10);
        return ResponseEntity.ok(service.getByEntityTypeAndEntityId(entityType, entityId, pageable));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> count(
            @RequestParam String entityType,
            @RequestParam Long entityId) {
        return ResponseEntity.ok(Map.of("count", service.countByEntityTypeAndEntityId(entityType, entityId)));
    }
}
