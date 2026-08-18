package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.UserPermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserPermissionResponse;
import com.brilliantsofts.EliteUniversity.service.UserPermissionService;
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
@RequestMapping("/api/user-permissions")
@RequiredArgsConstructor
public class UserPermissionController {
    private final UserPermissionService service;

    @PostMapping
    public ResponseEntity<UserPermissionResponse> create(@RequestBody UserPermissionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserPermissionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<Page<UserPermissionResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(pageable));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPermissionResponse>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }
    @GetMapping("/user/{userId}/effective")
    public ResponseEntity<List<UserPermissionResponse>> getEffectivePermissions(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserPermissionResponse> update(@PathVariable Long id, @RequestBody UserPermissionRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<Void> bulkUpdate(@RequestBody Object permissions) {
        return ResponseEntity.ok().build();
    }
}
