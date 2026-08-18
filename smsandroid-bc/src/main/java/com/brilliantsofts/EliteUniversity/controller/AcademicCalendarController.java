package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AcademicCalendarRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AcademicCalendarResponse;
import com.brilliantsofts.EliteUniversity.service.AcademicCalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/academic-calendars", "/api/academic-calendar-events"})
@RequiredArgsConstructor
public class AcademicCalendarController {
    private final AcademicCalendarService service;

    @PostMapping
    public ResponseEntity<AcademicCalendarResponse> create(@RequestBody AcademicCalendarRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicCalendarResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AcademicCalendarResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(search, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicCalendarResponse> update(@PathVariable Long id, @RequestBody AcademicCalendarRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<java.util.List<AcademicCalendarResponse>> getBySemester(@PathVariable Long semesterId) {
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }

    @GetMapping("/upcoming")
    public ResponseEntity<java.util.List<AcademicCalendarResponse>> getUpcoming() {
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }

    @GetMapping("/holidays/{semesterId}")
    public ResponseEntity<java.util.List<AcademicCalendarResponse>> getHolidays(@PathVariable Long semesterId) {
        return ResponseEntity.ok(java.util.Collections.emptyList());
    }
}
