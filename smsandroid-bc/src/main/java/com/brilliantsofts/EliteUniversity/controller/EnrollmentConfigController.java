package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.EnrollmentConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EnrollmentConfigResponse;
import com.brilliantsofts.EliteUniversity.service.EnrollmentConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollment-configs")
@RequiredArgsConstructor
public class EnrollmentConfigController {
    private final EnrollmentConfigService service;

    @PostMapping
    public ResponseEntity<EnrollmentConfigResponse> create(@RequestBody EnrollmentConfigRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentConfigResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentConfigResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<EnrollmentConfigResponse>> getActive() {
        return ResponseEntity.ok(service.getActive());
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<EnrollmentConfigResponse> getBySemester(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getBySemester(semesterId));
    }

    @GetMapping("/check/{semesterId}")
    public ResponseEntity<Boolean> isEnrollmentOpen(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.isEnrollmentOpen(semesterId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentConfigResponse> update(@PathVariable Long id, @RequestBody EnrollmentConfigRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<EnrollmentConfigResponse> closeEnrollment(@PathVariable Long id) {
        return ResponseEntity.ok(service.closeEnrollment(id));
    }

    @PostMapping("/{id}/reopen")
    public ResponseEntity<EnrollmentConfigResponse> reopenEnrollment(@PathVariable Long id) {
        return ResponseEntity.ok(service.reopenEnrollment(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
