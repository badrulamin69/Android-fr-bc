package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionWaitingListEntryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionWaitingListEntryResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionWaitingListEntryService;
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
@RequestMapping("/api/admission-waiting-lists")
@RequiredArgsConstructor
public class AdmissionWaitingListEntryController {
    private final AdmissionWaitingListEntryService service;

    @GetMapping("/{waitingListId}/entries")
    public ResponseEntity<Page<AdmissionWaitingListEntryResponse>> getByWaitingListId(
            @PathVariable Long waitingListId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "rank") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getByWaitingListId(waitingListId, pageable));
    }

    @GetMapping("/{waitingListId}/entries/all")
    public ResponseEntity<List<AdmissionWaitingListEntryResponse>> getAllByWaitingListId(@PathVariable Long waitingListId) {
        return ResponseEntity.ok(service.getAllByWaitingListId(waitingListId));
    }

    @PostMapping("/{waitingListId}/entries")
    public ResponseEntity<AdmissionWaitingListEntryResponse> create(
            @PathVariable Long waitingListId,
            @RequestBody AdmissionWaitingListEntryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(waitingListId, request));
    }

    @PutMapping("/entries/{entryId}")
    public ResponseEntity<AdmissionWaitingListEntryResponse> update(
            @PathVariable Long entryId,
            @RequestBody AdmissionWaitingListEntryRequest request) {
        return ResponseEntity.ok(service.update(entryId, request));
    }

    @PutMapping("/entries/{entryId}/status")
    public ResponseEntity<AdmissionWaitingListEntryResponse> updateStatus(
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
