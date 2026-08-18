package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.StudentProfileRequest;
import com.brilliantsofts.EliteUniversity.dto.response.StudentProfileResponse;
import com.brilliantsofts.EliteUniversity.service.StudentProfileService;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.entity.StudentProfile;
import com.brilliantsofts.EliteUniversity.repository.StudentProfileRepository;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student-profiles")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentProfileService service;
    private final StudentRepository studentRepository;
    private final StudentProfileRepository profileRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentProfileResponse> getMyProfile(@AuthenticationPrincipal CustomUserDetails principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Student student = studentRepository.findByUserId(principal.getId());
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        StudentProfile profile = profileRepository.findByStudentId(student.getId()).orElse(null);
        if (profile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(com.brilliantsofts.EliteUniversity.dto.mapper.StudentProfileMapper.toResponse(profile));
    }

    @PostMapping
    public ResponseEntity<StudentProfileResponse> create(@RequestBody StudentProfileRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfileResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<StudentProfileResponse>> getAll(
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
    public ResponseEntity<StudentProfileResponse> update(@PathVariable Long id, @RequestBody StudentProfileRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
