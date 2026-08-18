package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.CourseRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CourseRegistrationResponse;
import com.brilliantsofts.EliteUniversity.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService service;

    @PostMapping("/select")
    public ResponseEntity<CourseRegistrationResponse> selectCourse(@RequestBody CourseRegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.selectCourse(request));
    }

    @PostMapping("/drop/{registrationId}")
    public ResponseEntity<Void> dropCourse(@PathVariable Long registrationId) {
        service.dropCourse(registrationId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/finalize/{registrationId}")
    public ResponseEntity<CourseRegistrationResponse> finalizeRegistration(@PathVariable Long registrationId) {
        return ResponseEntity.ok(service.finalizeRegistration(registrationId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseRegistrationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CourseRegistrationResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long semesterId) {
        Sort sortObj = direction.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return ResponseEntity.ok(service.getAll(status, semesterId, pageable));
    }

    @GetMapping("/student/{studentId}/semester/{semesterId}")
    public ResponseEntity<java.util.Map<String, Object>> getStudentRegistration(@PathVariable Long studentId, @PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getStudentRegistration(studentId, semesterId));
    }

    @GetMapping("/summary/{studentId}/semester/{semesterId}")
    public ResponseEntity<java.util.Map<String, Object>> getRegistrationSummary(@PathVariable Long studentId, @PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getRegistrationSummary(studentId, semesterId));
    }

    @PostMapping("/payment")
    public ResponseEntity<Void> processPayment(@RequestBody java.util.Map<String, Object> paymentRequest) {
        service.processPayment(paymentRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dashboard/{semesterId}")
    public ResponseEntity<java.util.Map<String, Object>> getDashboard(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getDashboard(semesterId));
    }

    @GetMapping("/eligibility/{studentId}/semester/{semesterId}")
    public ResponseEntity<java.util.Map<String, Object>> checkEligibility(@PathVariable Long studentId, @PathVariable Long semesterId) {
        return ResponseEntity.ok(service.checkEligibility(studentId, semesterId));
    }

    @GetMapping("/validate/{studentId}/subject/{subjectId}/semester/{semesterId}")
    public ResponseEntity<java.util.Map<String, Object>> validateSubject(@PathVariable Long studentId, @PathVariable Long subjectId, @PathVariable Long semesterId) {
        return ResponseEntity.ok(service.validateSubject(studentId, subjectId, semesterId));
    }

    @GetMapping("/history/student/{studentId}")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> getStudentHistory(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getStudentHistory(studentId));
    }

    @GetMapping("/history/semester/{semesterId}")
    public ResponseEntity<java.util.List<java.util.Map<String, Object>>> getSemesterHistory(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getSemesterHistory(semesterId));
    }
}
