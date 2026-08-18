package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionWaitingListRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionWaitingListResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionWaitingListService;
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
@RequestMapping("/api/admission-waiting-lists")
@RequiredArgsConstructor
public class AdmissionWaitingListController {
    private final AdmissionWaitingListService service;

    @PostMapping
    public ResponseEntity<AdmissionWaitingListResponse> create(@RequestBody AdmissionWaitingListRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionWaitingListResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionWaitingListResponse>> getAll(
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
    public ResponseEntity<AdmissionWaitingListResponse> update(@PathVariable Long id, @RequestBody AdmissionWaitingListRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generate")
    public ResponseEntity<AdmissionWaitingListResponse> generate(
            @RequestParam Long testId,
            @RequestParam String name,
            @RequestParam Integer totalSlots) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(testId, name, totalSlots));
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<AdmissionWaitingListResponse> publish(@PathVariable Long id) {
        return ResponseEntity.ok(service.publish(id));
    }

    @PutMapping("/{id}/unpublish")
    public ResponseEntity<AdmissionWaitingListResponse> unpublish(@PathVariable Long id) {
        return ResponseEntity.ok(service.unpublish(id));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }
}
