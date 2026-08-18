package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.SemesterEnrollmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SemesterEnrollmentResponse;
import com.brilliantsofts.EliteUniversity.service.SemesterEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/semester-enrollments")
@RequiredArgsConstructor
public class SemesterEnrollmentController {

    private final SemesterEnrollmentService service;

    @PostMapping
    public ResponseEntity<SemesterEnrollmentResponse> create(@RequestBody SemesterEnrollmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemesterEnrollmentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<SemesterEnrollmentResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String search) {
        Sort sortObj = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return ResponseEntity.ok(service.getAll(search, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemesterEnrollmentResponse> update(@PathVariable Long id, @RequestBody SemesterEnrollmentRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/eligibility/{studentId}/semester/{semesterId}")
    public ResponseEntity<java.util.Map<String, Object>> getEligibility(@PathVariable Long studentId, @PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getEligibility(studentId, semesterId));
    }

    @PostMapping("/enroll")
    public ResponseEntity<Void> enroll(@RequestBody java.util.Map<String, Object> request) {
        service.enroll(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/force-enroll")
    public ResponseEntity<Void> forceEnroll(@RequestBody java.util.Map<String, Object> request) {
        service.forceEnroll(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<java.util.Map<String, Object>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getByStudent(studentId));
    }

    @GetMapping("/student/{studentId}/semester/{semesterId}")
    public ResponseEntity<java.util.Map<String, Object>> getByStudentAndSemester(@PathVariable Long studentId, @PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getByStudentAndSemester(studentId, semesterId));
    }

    @GetMapping("/pending/semester/{semesterId}")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> getPendingBySemester(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getPendingBySemester(semesterId));
    }

    @GetMapping("/pending/advisor/{advisorId}/semester/{semesterId}")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> getPendingByAdvisor(@PathVariable Long advisorId, @PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getPendingByAdvisor(advisorId, semesterId));
    }

    @PostMapping("/approval")
    public ResponseEntity<Void> approve(@RequestBody java.util.Map<String, Object> request) {
        service.approve(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{enrollmentId}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable Long enrollmentId) {
        service.cancel(enrollmentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{enrollmentId}/reopen")
    public ResponseEntity<Void> reopen(@PathVariable Long enrollmentId) {
        service.reopen(enrollmentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{enrollmentId}/finalize")
    public ResponseEntity<Void> finalizeEnrollment(@PathVariable Long enrollmentId) {
        service.finalizeEnrollment(enrollmentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dashboard/{semesterId}")
    public ResponseEntity<java.util.Map<String, Object>> getDashboard(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getDashboard(semesterId));
    }

    @GetMapping("/history/student/{studentId}")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> getHistoryByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getHistoryByStudent(studentId));
    }

    @GetMapping("/history/semester/{semesterId}")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> getHistoryBySemester(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getHistoryBySemester(semesterId));
    }

    @GetMapping("/history/enrollment/{enrollmentId}")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> getHistoryByEnrollment(@PathVariable Long enrollmentId) {
        return ResponseEntity.ok(service.getHistoryByEnrollment(enrollmentId));
    }
}
