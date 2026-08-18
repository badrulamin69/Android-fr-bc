package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.SeatAllocationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SeatAllocationResponse;
import com.brilliantsofts.EliteUniversity.service.SeatAllocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seat-allocations")
@RequiredArgsConstructor
public class SeatAllocationController {

    private final SeatAllocationService service;

    @PostMapping
    public ResponseEntity<SeatAllocationResponse> create(@RequestBody SeatAllocationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatAllocationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SeatAllocationResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy, sortDir, search));
    }

    @GetMapping("/test/{testId}")
    public ResponseEntity<List<SeatAllocationResponse>> findByTestId(@PathVariable Long testId) {
        return ResponseEntity.ok(service.findByTestId(testId));
    }

    @PostMapping("/auto-generate/{testId}")
    public ResponseEntity<List<SeatAllocationResponse>> autoGenerate(@PathVariable Long testId) {
        return ResponseEntity.ok(service.autoGenerate(testId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatAllocationResponse> update(@PathVariable Long id, @RequestBody SeatAllocationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
