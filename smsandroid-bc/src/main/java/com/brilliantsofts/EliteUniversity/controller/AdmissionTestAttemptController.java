package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.response.AdmissionTestAttemptResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionTestAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admission-test-attempts")
@RequiredArgsConstructor
public class AdmissionTestAttemptController {

    private final AdmissionTestAttemptService service;

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionTestAttemptResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionTestAttemptResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.getAll(search, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
