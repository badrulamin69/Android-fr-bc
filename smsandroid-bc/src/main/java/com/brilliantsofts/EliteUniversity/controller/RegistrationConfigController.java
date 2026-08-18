package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.RegistrationConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RegistrationConfigResponse;
import com.brilliantsofts.EliteUniversity.service.RegistrationConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/registration-configs")
@RequiredArgsConstructor
public class RegistrationConfigController {

    private final RegistrationConfigService service;

    @PostMapping
    public ResponseEntity<RegistrationConfigResponse> create(@RequestBody RegistrationConfigRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationConfigResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RegistrationConfigResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<RegistrationConfigResponse>> getActive() {
        return ResponseEntity.ok(service.getActive());
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<RegistrationConfigResponse> getBySemester(@PathVariable Long semesterId) {
        return ResponseEntity.ok(service.getBySemester(semesterId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegistrationConfigResponse> update(@PathVariable Long id, @RequestBody RegistrationConfigRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<RegistrationConfigResponse> closeRegistration(@PathVariable Long id) {
        return ResponseEntity.ok(service.closeRegistration(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
