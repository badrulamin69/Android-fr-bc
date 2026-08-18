package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionTestResultRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestResultResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantTestResultView;
import com.brilliantsofts.EliteUniversity.service.AdmissionTestResultService;
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
@RequestMapping("/api/admission-test-results")
@RequiredArgsConstructor
public class AdmissionTestResultController {
    private final AdmissionTestResultService service;

    @PostMapping
    public ResponseEntity<AdmissionTestResultResponse> create(@RequestBody AdmissionTestResultRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionTestResultResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionTestResultResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(pageable, search));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionTestResultResponse> update(@PathVariable Long id, @RequestBody AdmissionTestResultRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/applicants")
    public ResponseEntity<Page<ApplicantTestResultView>> getApplicantsWithResults(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getApplicantsWithResults(pageable, search));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<AdmissionTestResultResponse>> bulkSave(@RequestBody List<AdmissionTestResultRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.bulkSave(requests));
    }
}
