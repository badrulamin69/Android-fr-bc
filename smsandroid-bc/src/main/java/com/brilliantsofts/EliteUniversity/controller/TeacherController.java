package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.TeacherRequest;
import com.brilliantsofts.EliteUniversity.dto.response.TeacherResponse;
import com.brilliantsofts.EliteUniversity.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService service;

    @PostMapping
    public ResponseEntity<TeacherResponse> create(@RequestBody TeacherRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TeacherResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long facultyId,
            @RequestParam(required = false) String designation,
            @RequestParam(required = false) String status) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(search, departmentId, facultyId, designation, status, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> update(@PathVariable Long id, @RequestBody TeacherRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        return ResponseEntity.ok(service.getDashboard());
    }

    @GetMapping("/{id}/documents")
    public ResponseEntity<java.util.List<Map<String, Object>>> getDocuments(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDocuments(id));
    }

    @PostMapping("/{id}/documents")
    public ResponseEntity<Map<String, Object>> addDocument(@PathVariable Long id, @RequestBody Map<String, Object> document) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addDocument(id, document));
    }

    @GetMapping("/{id}/course-assignments")
    public ResponseEntity<java.util.List<Map<String, Object>>> getCourseAssignments(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCourseAssignments(id));
    }

    @GetMapping("/{id}/publications")
    public ResponseEntity<java.util.List<Map<String, Object>>> getPublications(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPublications(id));
    }

    @GetMapping("/{id}/leaves")
    public ResponseEntity<java.util.List<Map<String, Object>>> getLeaves(@PathVariable Long id) {
        return ResponseEntity.ok(service.getLeaves(id));
    }

    @GetMapping("/{id}/attendance")
    public ResponseEntity<java.util.List<Map<String, Object>>> getAttendance(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAttendance(id));
    }
}
