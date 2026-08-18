package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.SeatAllocationConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SeatAllocationConfigResponse;
import com.brilliantsofts.EliteUniversity.service.SeatAllocationConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seat-allocation-configs")
@RequiredArgsConstructor
public class SeatAllocationConfigController {

    private final SeatAllocationConfigService service;

    @PostMapping
    public ResponseEntity<SeatAllocationConfigResponse> create(@RequestBody SeatAllocationConfigRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatAllocationConfigResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SeatAllocationConfigResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy, sortDir, search));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatAllocationConfigResponse> update(@PathVariable Long id, @RequestBody SeatAllocationConfigRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<SeatAllocationConfigResponse> activate(@PathVariable Long id) {
        return ResponseEntity.ok(service.activate(id));
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<SeatAllocationConfigResponse> close(@PathVariable Long id) {
        return ResponseEntity.ok(service.close(id));
    }
}
