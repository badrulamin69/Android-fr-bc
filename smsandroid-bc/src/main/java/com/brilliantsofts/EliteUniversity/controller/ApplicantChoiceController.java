package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.response.ApplicantChoiceResponse;
import com.brilliantsofts.EliteUniversity.dto.response.ApplicantChoiceSubmissionResponse;
import com.brilliantsofts.EliteUniversity.entity.Applicant;
import com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration;
import com.brilliantsofts.EliteUniversity.entity.Program;
import com.brilliantsofts.EliteUniversity.repository.ApplicantRepository;
import com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository;
import com.brilliantsofts.EliteUniversity.repository.ProgramRepository;
import com.brilliantsofts.EliteUniversity.security.CustomUserDetails;
import com.brilliantsofts.EliteUniversity.service.ApplicantChoiceSubmissionService;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applicant-choices")
@RequiredArgsConstructor
public class ApplicantChoiceController {
    private final ApplicantChoiceSubmissionService service;
    private final ProgramRepository programRepository;
    private final ApplicantRepository applicantRepository;
    private final PreAdmissionRegistrationRepository preAdmissionRepository;

    @PostMapping("/start/{configId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<ApplicantChoiceSubmissionResponse> startSubmission(
            @PathVariable Long configId,
            @RequestParam(required = false) Long registrationId,
            @RequestParam(required = false) String applicantName,
            @RequestParam(required = false) Integer meritRank,
            @RequestParam(required = false) Double meritScore,
            @AuthenticationPrincipal CustomUserDetails principal) {
        
        Long resolvedRegId = registrationId;
        String resolvedName = applicantName;

        if (resolvedRegId == null && principal != null) {
            Applicant applicant = applicantRepository.findByUserId(principal.getId());
            if (applicant != null) {
                resolvedRegId = applicant.getId();
                resolvedName = applicant.getFullName();
            } else {
                PreAdmissionRegistration reg = preAdmissionRepository.findByEmail(principal.getUsername()).orElse(null);
                if (reg != null) {
                    resolvedRegId = reg.getId();
                    resolvedName = reg.getFirstName() + " " + (reg.getLastName() != null ? reg.getLastName() : "");
                } else {
                    resolvedRegId = principal.getId();
                    resolvedName = principal.getUsername();
                }
            }
        }
        if (resolvedRegId == null) resolvedRegId = 1L;
        if (resolvedName == null || resolvedName.isBlank()) resolvedName = "Applicant #" + resolvedRegId;

        return ResponseEntity.status(HttpStatus.CREATED).body(service.startSubmission(configId, resolvedRegId, resolvedName, meritRank, meritScore));
    }

    @GetMapping("/my-submission")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<ApplicantChoiceSubmissionResponse> getMySubmission(
            @RequestParam Long configId,
            @RequestParam(required = false) Long registrationId,
            @AuthenticationPrincipal CustomUserDetails principal) {
        Long resolvedRegId = registrationId;
        if (resolvedRegId == null && principal != null) {
            Applicant applicant = applicantRepository.findByUserId(principal.getId());
            if (applicant != null) {
                resolvedRegId = applicant.getId();
            } else {
                PreAdmissionRegistration reg = preAdmissionRepository.findByEmail(principal.getUsername()).orElse(null);
                resolvedRegId = reg != null ? reg.getId() : principal.getId();
            }
        }
        if (resolvedRegId == null) resolvedRegId = 1L;
        return ResponseEntity.ok(service.getMySubmission(resolvedRegId, configId));
    }

    @GetMapping("/my-choices")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<List<ApplicantChoiceResponse>> getMyChoices(@RequestParam Long submissionId) {
        return ResponseEntity.ok(service.getMyChoices(submissionId));
    }

    @PostMapping("/add-choice/{submissionId}/{programId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<ApplicantChoiceResponse> addChoice(
            @PathVariable Long submissionId,
            @PathVariable Long programId,
            @RequestParam(required = false) String programName,
            @RequestParam(required = false) Long facultyId,
            @RequestParam(required = false) String facultyName,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) String shift) {
        
        String pName = programName;
        Long fId = facultyId;
        String fName = facultyName;
        Long dId = departmentId;
        String dName = departmentName;

        if (pName == null || fId == null || dId == null) {
            Program program = programRepository.findById(programId).orElse(null);
            if (program != null) {
                if (pName == null) pName = program.getName();
                if (dId == null && program.getDepartment() != null) {
                    dId = program.getDepartment().getId();
                    if (dName == null) dName = program.getDepartment().getName();
                    if (fId == null && program.getDepartment().getFaculty() != null) {
                        fId = program.getDepartment().getFaculty().getId();
                        if (fName == null) fName = program.getDepartment().getFaculty().getName();
                    }
                }
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.addChoice(submissionId, programId, pName, fId, fName, dId, dName, shift));
    }

    @DeleteMapping("/remove-choice/{choiceId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<Void> removeChoice(@PathVariable Long choiceId) {
        service.removeChoice(choiceId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/move-choice/{choiceId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<ApplicantChoiceResponse> moveChoice(
            @PathVariable Long choiceId,
            @RequestParam String direction) {
        return ResponseEntity.ok(service.moveChoice(choiceId, direction));
    }

    @PostMapping("/submit/{submissionId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<ApplicantChoiceSubmissionResponse> submit(@PathVariable Long submissionId) {
        return ResponseEntity.ok(service.submit(submissionId));
    }

    @GetMapping("/admin/submissions")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Page<ApplicantChoiceSubmissionResponse>> getAdminSubmissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Long configId) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAdminSubmissions(pageable, search, status, configId));
    }

    @GetMapping("/admin/submissions/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<ApplicantChoiceSubmissionResponse> getAdminSubmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAdminSubmissionById(id));
    }

    @GetMapping("/admin/submissions/{submissionId}/choices")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<List<ApplicantChoiceResponse>> getAdminSubmissionChoices(@PathVariable String submissionId) {
        return ResponseEntity.ok(service.getAdminSubmissionChoices(submissionId));
    }

    @PutMapping("/admin/submissions/{id}/lock")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<ApplicantChoiceSubmissionResponse> lockSubmission(@PathVariable Long id) {
        return ResponseEntity.ok(service.lockSubmission(id));
    }

    @PutMapping("/admin/submissions/{id}/reopen")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<ApplicantChoiceSubmissionResponse> reopenSubmission(@PathVariable Long id) {
        return ResponseEntity.ok(service.reopenSubmission(id));
    }

    @GetMapping("/admin/stats/{configId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Map<String, Object>> getAdminStats(@PathVariable Long configId) {
        return ResponseEntity.ok(service.getAdminStats(configId));
    }

    @GetMapping("/admin/available-programs/{configId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<List<Map<String, Object>>> getAvailablePrograms(@PathVariable Long configId) {
        List<Program> all = programRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Program p : all) {
            Map<String, Object> map = new HashMap<>();
            map.put("programId", p.getId());
            map.put("programName", p.getName());
            map.put("programCode", p.getCode());
            map.put("departmentId", p.getDepartment() != null ? p.getDepartment().getId() : null);
            map.put("departmentName", p.getDepartment() != null ? p.getDepartment().getName() : "General Department");
            if (p.getDepartment() != null && p.getDepartment().getFaculty() != null) {
                map.put("facultyId", p.getDepartment().getFaculty().getId());
                map.put("facultyName", p.getDepartment().getFaculty().getName());
            } else {
                map.put("facultyId", 1L);
                map.put("facultyName", "Faculty of Science & Engineering");
            }
            map.put("shift", "Day");
            map.put("totalSeats", p.getTotalCredits() != null ? p.getTotalCredits().intValue() : 60);
            map.put("availableSeats", 40);
            result.add(map);
        }
        return ResponseEntity.ok(result);
    }
}
