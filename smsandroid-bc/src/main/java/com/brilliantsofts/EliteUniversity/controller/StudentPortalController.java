package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.StudentPortalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student-portal")
@RequiredArgsConstructor
public class StudentPortalController {

    private final StudentPortalService service;
    private final UserRepository userRepository;

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) return null;
        User user = userRepository.findByUsername(auth.getName()).orElse(null);
        return user != null ? user.getId() : null;
    }

    @GetMapping("/my-enrollment")
    public ResponseEntity<Object> getMyEnrollment() {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getMyEnrollment(userId));
    }
}
