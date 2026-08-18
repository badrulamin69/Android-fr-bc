package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionMeritListEntryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionMeritListEntryResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionMeritListEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admission-merit-lists")
@RequiredArgsConstructor
public class AdmissionMeritListEntryController {
    private final AdmissionMeritListEntryService service;

    @GetMapping("/{meritListId}/entries")
    public ResponseEntity<Page<AdmissionMeritListEntryResponse>> getByMeritListId(
            @PathVariable Long meritListId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "rank") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getByMeritListIdWithFilter(meritListId, search, status, pageable));
    }

    @GetMapping("/{meritListId}/entries/all")
    public ResponseEntity<List<AdmissionMeritListEntryResponse>> getAllByMeritListId(@PathVariable Long meritListId) {
        return ResponseEntity.ok(service.getAllByMeritListId(meritListId));
    }

    @PostMapping("/{meritListId}/entries")
    public ResponseEntity<AdmissionMeritListEntryResponse> create(
            @PathVariable Long meritListId,
            @RequestBody AdmissionMeritListEntryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(meritListId, request));
    }

    @PutMapping("/entries/{entryId}")
    public ResponseEntity<AdmissionMeritListEntryResponse> update(
            @PathVariable Long entryId,
            @RequestBody AdmissionMeritListEntryRequest request) {
        return ResponseEntity.ok(service.update(entryId, request));
    }

    @PutMapping("/entries/{entryId}/status")
    public ResponseEntity<AdmissionMeritListEntryResponse> updateStatus(
            @PathVariable Long entryId,
            @RequestParam String status) {
        return ResponseEntity.ok(service.updateStatus(entryId, status));
    }

    @DeleteMapping("/entries/{entryId}")
    public ResponseEntity<Void> delete(@PathVariable Long entryId) {
        service.delete(entryId);
        return ResponseEntity.noContent().build();
    }
}
