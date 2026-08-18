package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionCircularRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionCircularResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionCircularService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admission-circulars")
@RequiredArgsConstructor
public class AdmissionCircularController {
    private final AdmissionCircularService service;

    @PostMapping
    public ResponseEntity<AdmissionCircularResponse> create(@RequestBody AdmissionCircularRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmissionCircularResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmissionCircularResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String direction) {
        String actualSortBy = sortBy;
        String actualSortDir = sortDir;
        if (sort != null && !sort.isEmpty()) {
            actualSortBy = sort;
        }
        if (direction != null && !direction.isEmpty()) {
            actualSortDir = direction;
        }
        Sort sortObj = actualSortDir.equalsIgnoreCase("desc") ? Sort.by(actualSortBy).descending() : Sort.by(actualSortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmissionCircularResponse> update(@PathVariable Long id, @RequestBody AdmissionCircularRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
