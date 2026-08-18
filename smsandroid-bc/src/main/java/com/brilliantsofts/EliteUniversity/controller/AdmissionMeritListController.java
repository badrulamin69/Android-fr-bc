package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionMeritListRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionMeritListResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionMeritListService;
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
@RequestMapping("/api/admission-merit-lists")
@RequiredArgsConstructor
public class AdmissionMeritListController {
    private final AdmissionMeritListService service;

    @PostMapping
    public ResponseEntity<AdmissionMeritListResponse> create(@RequestBody AdmissionMeritListRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionMeritListResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionMeritListResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionMeritListResponse> update(@PathVariable Long id, @RequestBody AdmissionMeritListRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generate")
    public ResponseEntity<AdmissionMeritListResponse> generate(
            @RequestParam Long testId,
            @RequestParam String listName,
            @RequestParam Integer totalSeats) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(testId, listName, totalSeats));
    }

    @PostMapping("/generate-by-circular")
    public ResponseEntity<AdmissionMeritListResponse> generateByCircular(
            @RequestParam Long circularId,
            @RequestParam(required = false) String listName,
            @RequestParam(required = false, defaultValue = "100") Integer totalSeats,
            @RequestParam(required = false, defaultValue = "60.0") Double cutoffScore) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generateByCircular(circularId, listName, totalSeats, cutoffScore));
    }

    @PutMapping("/circular/{circularId}/publish")
    public ResponseEntity<AdmissionMeritListResponse> publishByCircular(@PathVariable Long circularId) {
        return ResponseEntity.ok(service.publishByCircular(circularId));
    }

    @GetMapping("/circular/{circularId}")
    public ResponseEntity<java.util.List<AdmissionMeritListResponse>> getByCircularId(@PathVariable Long circularId) {
        return ResponseEntity.ok(service.getByCircularId(circularId));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<AdmissionMeritListResponse> publish(@PathVariable Long id) {
        return ResponseEntity.ok(service.publish(id));
    }

    @PutMapping("/{id}/unpublish")
    public ResponseEntity<AdmissionMeritListResponse> unpublish(@PathVariable Long id) {
        return ResponseEntity.ok(service.unpublish(id));
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<AdmissionMeritListResponse> archive(@PathVariable Long id) {
        return ResponseEntity.ok(service.archive(id));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }
}
