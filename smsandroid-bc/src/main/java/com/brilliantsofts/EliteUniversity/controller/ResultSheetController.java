package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.response.ResultSheetResponse;
import com.brilliantsofts.EliteUniversity.dto.response.StudentResultSummary;
import com.brilliantsofts.EliteUniversity.entity.Student;
import com.brilliantsofts.EliteUniversity.repository.StudentRepository;
import com.brilliantsofts.EliteUniversity.security.CustomUserDetails;
import com.brilliantsofts.EliteUniversity.service.ResultSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/result-sheets")
@RequiredArgsConstructor
public class ResultSheetController {

    private final ResultSheetService service;
    private final StudentRepository studentRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public ResultSheetResponse me(@RequestParam(required = false) String semester,
                                  @AuthenticationPrincipal CustomUserDetails principal) {
        Student student = studentRepository.findByUserId(principal.getId());
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No student profile is linked to the current user");
        }
        return service.getForStudent(student.getId(), semester);
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','DEPARTMENT_HEAD','TEACHER','ADVISOR','ADMISSION_OFFICER','STAFF')")
    public ResultSheetResponse byStudent(@PathVariable Long studentId,
                                         @RequestParam(required = false) String semester) {
        return service.getForStudent(studentId, semester);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','DEPARTMENT_HEAD','TEACHER','ADVISOR','ADMISSION_OFFICER','STAFF')")
    public Page<StudentResultSummary> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long facultyId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Long programId,
            @RequestParam(required = false) Long academicSessionId,
            @RequestParam(required = false) String semester,
            @RequestParam(required = false) String status) {
        return service.getStudentSummaries(page, size, search, facultyId, departmentId,
                programId, academicSessionId, semester, status);
    }

    @GetMapping("/semesters")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','DEPARTMENT_HEAD','TEACHER','ADVISOR','ADMISSION_OFFICER','STAFF')")
    public List<String> semesters() {
        return service.getSemesters();
    }

    @GetMapping("/student/{studentId}/cgpa")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','DEPARTMENT_HEAD','TEACHER','ADVISOR','ADMISSION_OFFICER','STAFF')")
    public Map<String, Double> cgpa(@PathVariable Long studentId) {
        Map<String, Double> result = new HashMap<>();
        result.put("cgpa", service.getCgpa(studentId));
        return result;
    }

    @GetMapping("/student/{studentId}/download")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','DEPARTMENT_HEAD','TEACHER','ADVISOR','ADMISSION_OFFICER','STAFF')")
    public org.springframework.http.ResponseEntity<byte[]> downloadResultSheet(
            @PathVariable Long studentId,
            @RequestParam(required = false) String semester) {
        byte[] bytes = service.generateResultSheetPdf(studentId, semester);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.TEXT_HTML);
        headers.setContentDisposition(org.springframework.http.ContentDisposition.inline()
                .filename("result-sheet-student-" + studentId + (semester != null ? "-" + semester.replace(" ", "_") : "") + ".html")
                .build());
        return org.springframework.http.ResponseEntity.ok().headers(headers).body(bytes);
    }

    @GetMapping("/student/{studentId}/pdf")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','DEPARTMENT_HEAD','TEACHER','ADVISOR','ADMISSION_OFFICER','STAFF')")
    public org.springframework.http.ResponseEntity<byte[]> getResultSheetPdf(
            @PathVariable Long studentId,
            @RequestParam(required = false) String semester) {
        return downloadResultSheet(studentId, semester);
    }

    @GetMapping("/me/download")
    @PreAuthorize("hasRole('STUDENT')")
    public org.springframework.http.ResponseEntity<byte[]> downloadMyResultSheet(
            @RequestParam(required = false) String semester,
            @AuthenticationPrincipal CustomUserDetails principal) {
        Student student = studentRepository.findByUserId(principal.getId());
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No student profile is linked to current user");
        }
        return downloadResultSheet(student.getId(), semester);
    }

    @GetMapping("/me/pdf")
    @PreAuthorize("hasRole('STUDENT')")
    public org.springframework.http.ResponseEntity<byte[]> getMyResultSheetPdf(
            @RequestParam(required = false) String semester,
            @AuthenticationPrincipal CustomUserDetails principal) {
        return downloadMyResultSheet(semester, principal);
    }
}
