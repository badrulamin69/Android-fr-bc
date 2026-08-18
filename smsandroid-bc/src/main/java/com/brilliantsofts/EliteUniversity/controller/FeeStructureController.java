package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.FeeStructureRequest;
import com.brilliantsofts.EliteUniversity.dto.response.FeeStructureResponse;
import com.brilliantsofts.EliteUniversity.service.FeeStructureService;
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
@RequestMapping("/api/fee-structures")
@RequiredArgsConstructor
public class FeeStructureController {
    private final FeeStructureService service;

    @PostMapping
    public ResponseEntity<FeeStructureResponse> create(@RequestBody FeeStructureRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeStructureResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<FeeStructureResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/semester/{semesterId}/program/{programId}")
    public ResponseEntity<List<FeeStructureResponse>> getBySemesterAndProgram(
            @PathVariable Long semesterId, @PathVariable Long programId) {
        return ResponseEntity.ok(service.getBySemesterAndProgram(semesterId, programId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeStructureResponse> update(@PathVariable Long id, @RequestBody FeeStructureRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
