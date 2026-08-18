package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionConfirmationRequest;
import com.brilliantsofts.EliteUniversity.dto.request.DocumentSubmitRequest;
import com.brilliantsofts.EliteUniversity.dto.request.DocumentVerifyRequest;
import com.brilliantsofts.EliteUniversity.dto.request.FeePaymentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionConfirmationResponse;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionDocumentResponse;
import com.brilliantsofts.EliteUniversity.service.AdmissionConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admission-confirmations")
@RequiredArgsConstructor
public class AdmissionConfirmationController {
    private final AdmissionConfirmationService service;
    private final com.brilliantsofts.EliteUniversity.repository.PreAdmissionRegistrationRepository registrationRepository;

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<AdmissionConfirmationResponse> create(@RequestBody AdmissionConfirmationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<AdmissionConfirmationResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Page<AdmissionConfirmationResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Boolean documentsVerified,
            @RequestParam(required = false) Boolean feePaid) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAll(pageable, search, status, documentsVerified, feePaid));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<AdmissionConfirmationResponse> update(@PathVariable Long id, @RequestBody AdmissionConfirmationRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<List<AdmissionConfirmationResponse>> getMy(
            @RequestParam(required = false) Long registrationId,
            org.springframework.security.core.Authentication authentication) {
        if (registrationId == null && authentication != null) {
            String email = authentication.getName();
            com.brilliantsofts.EliteUniversity.entity.PreAdmissionRegistration reg = registrationRepository.findByEmail(email).orElse(null);
            if (reg != null) {
                registrationId = reg.getId();
            }
        }
        if (registrationId == null) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(service.getMy(registrationId));
    }

    @PostMapping("/initiate/{allocationId}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<AdmissionConfirmationResponse> initiate(@PathVariable Long allocationId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.initiate(allocationId));
    }

    @PostMapping("/{confirmationId}/submit-documents")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<AdmissionConfirmationResponse> submitDocuments(
            @PathVariable Long confirmationId,
            @RequestBody List<DocumentSubmitRequest> documents) {
        return ResponseEntity.ok(service.submitDocuments(confirmationId, documents));
    }

    @PostMapping("/{confirmationId}/verify-documents")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<AdmissionConfirmationResponse> verifyDocuments(
            @PathVariable Long confirmationId,
            @RequestBody DocumentVerifyRequest request) {
        return ResponseEntity.ok(service.verifyDocuments(confirmationId, request));
    }

    @PostMapping("/{confirmationId}/pay-fee")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<AdmissionConfirmationResponse> payFee(
            @PathVariable Long confirmationId,
            @RequestBody FeePaymentRequest request) {
        return ResponseEntity.ok(service.payFee(confirmationId, request));
    }

    @PostMapping("/{confirmationId}/confirm")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<AdmissionConfirmationResponse> confirm(@PathVariable Long confirmationId) {
        return ResponseEntity.ok(service.confirm(confirmationId));
    }

    @GetMapping("/{confirmationId}/documents")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER', 'APPLICANT')")
    public ResponseEntity<List<AdmissionDocumentResponse>> getDocuments(@PathVariable Long confirmationId) {
        return ResponseEntity.ok(service.getDocuments(confirmationId));
    }

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'ADMISSION_OFFICER')")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(service.getStats());
    }
}
