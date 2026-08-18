package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.SystemSettingRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SystemSettingResponse;
import com.brilliantsofts.EliteUniversity.service.SystemSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/system-settings")
@RequiredArgsConstructor
public class SystemSettingController {

    private final SystemSettingService service;

    @PostMapping
    public ResponseEntity<SystemSettingResponse> create(@RequestBody SystemSettingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemSettingResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SystemSettingResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/by-module/{module}")
    public ResponseEntity<List<SystemSettingResponse>> getByModule(@PathVariable String module) {
        return ResponseEntity.ok(service.getByModule(module));
    }

    @GetMapping("/by-key/{key}")
    public ResponseEntity<SystemSettingResponse> getByKey(@PathVariable String key) {
        return ResponseEntity.ok(service.getByKey(key));
    }

    @GetMapping("/public")
    public ResponseEntity<List<SystemSettingResponse>> getPublic() {
        return ResponseEntity.ok(service.getPublic());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemSettingResponse> update(@PathVariable Long id, @RequestBody SystemSettingRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PutMapping("/batch")
    public ResponseEntity<Void> batchUpdate(@RequestBody List<SystemSettingRequest> settings) {
        service.batchUpdate(settings);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-key/{key}")
    public ResponseEntity<Void> deleteByKey(@PathVariable String key) {
        service.deleteByKey(key);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dropdowns")
    public ResponseEntity<java.util.Map<String, Object>> getDropdowns() {
        return ResponseEntity.ok(new java.util.HashMap<>());
    }

    @GetMapping("/system-info")
    public ResponseEntity<java.util.Map<String, Object>> getSystemInfo() {
        java.util.Map<String, Object> info = new java.util.HashMap<>();
        info.put("version", "1.0.0");
        info.put("status", "UP");
        return ResponseEntity.ok(info);
    }

    @PostMapping("/clear-cache")
    public ResponseEntity<Void> clearCache() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/seed")
    public ResponseEntity<Void> seedSettings() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset/{module}")
    public ResponseEntity<Void> resetModule(@PathVariable String module) {
        return ResponseEntity.ok().build();
    }
}
