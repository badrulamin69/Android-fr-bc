package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.response.LoginSessionResponse;
import com.brilliantsofts.EliteUniversity.service.LoginSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login-sessions")
@RequiredArgsConstructor
public class LoginSessionController {
    private final LoginSessionService service;

    @GetMapping("/{id}")
    public ResponseEntity<LoginSessionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping
    public ResponseEntity<Page<LoginSessionResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String search) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(pageable));
    }
    @GetMapping("/active")
    public ResponseEntity<List<LoginSessionResponse>> getActiveSessions() {
        return ResponseEntity.ok(service.getActiveSessions());
    }
    @GetMapping("/active/count")
    public ResponseEntity<Long> getActiveSessionCount() {
        return ResponseEntity.ok(service.getActiveSessionCount());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoginSessionResponse>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }
    @PostMapping("/{id}/terminate")
    public ResponseEntity<Void> terminateSession(@PathVariable Long id) {
        service.terminateSession(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/user/{userId}/terminate-all")
    public ResponseEntity<Void> terminateAllUserSessions(@PathVariable Long userId) {
        service.terminateAllUserSessions(userId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
