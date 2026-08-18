package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionOfferLetterRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionOfferLetterResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionOfferLetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admission-offer-letters")
@RequiredArgsConstructor
public class AdmissionOfferLetterController {

    private final AdmissionOfferLetterService service;

    @PostMapping
    public ResponseEntity<AdmissionOfferLetterResponse> create(@RequestBody AdmissionOfferLetterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionOfferLetterResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionOfferLetterResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy, sortDir, search));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionOfferLetterResponse> update(@PathVariable Long id, @RequestBody AdmissionOfferLetterRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<AdmissionOfferLetterResponse> accept(@PathVariable Long id) {
        return ResponseEntity.ok(service.accept(id));
    }

    @PutMapping("/{id}/decline")
    public ResponseEntity<AdmissionOfferLetterResponse> decline(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.decline(id, body.get("reason")));
    }
}
