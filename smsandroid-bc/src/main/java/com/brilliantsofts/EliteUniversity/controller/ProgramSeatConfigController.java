package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.ProgramSeatConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ProgramSeatConfigResponse;
import com.brilliantsofts.EliteUniversity.service.ProgramSeatConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/program-seat-configs")
@RequiredArgsConstructor
public class ProgramSeatConfigController {

    private final ProgramSeatConfigService service;

    @PostMapping
    public ResponseEntity<ProgramSeatConfigResponse> create(@RequestBody ProgramSeatConfigRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramSeatConfigResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProgramSeatConfigResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy, sortDir, search));
    }

    @GetMapping("/config/{configId}")
    public ResponseEntity<List<ProgramSeatConfigResponse>> findByConfigId(@PathVariable Long configId) {
        return ResponseEntity.ok(service.findByConfigId(configId));
    }

    @GetMapping("/config/{configId}/available")
    public ResponseEntity<List<ProgramSeatConfigResponse>> getAvailable(@PathVariable Long configId) {
        return ResponseEntity.ok(service.getAvailable(configId));
    }

    @GetMapping("/config/{configId}/summary")
    public ResponseEntity<Map<String, Object>> getSummary(@PathVariable Long configId) {
        return ResponseEntity.ok(service.getSummary(configId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramSeatConfigResponse> update(@PathVariable Long id, @RequestBody ProgramSeatConfigRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
