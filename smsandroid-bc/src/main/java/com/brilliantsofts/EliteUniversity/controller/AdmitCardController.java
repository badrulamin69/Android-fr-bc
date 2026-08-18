package com.brilliantsofts.EliteUniversity.controller;

import com.brilliantsofts.EliteUniversity.dto.request.AdmitCardRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmitCardResponse;
import com.brilliantsofts.EliteUniversity.service.AdmitCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admit-cards")
@RequiredArgsConstructor
public class AdmitCardController {

    private final AdmitCardService service;

    @PostMapping
    public ResponseEntity<AdmitCardResponse> create(@RequestBody AdmitCardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmitCardResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AdmitCardResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy, sortDir, search));
    }

    @GetMapping("/test/{testId}")
    public ResponseEntity<List<AdmitCardResponse>> findByTestId(@PathVariable Long testId) {
        return ResponseEntity.ok(service.findByTestId(testId));
    }

    @GetMapping("/registration/{regId}")
    public ResponseEntity<List<AdmitCardResponse>> findByRegistrationId(@PathVariable Long regId) {
        return ResponseEntity.ok(service.findByRegistrationId(regId));
    }

    @PostMapping("/generate/{testId}")
    public ResponseEntity<AdmitCardResponse> generate(@PathVariable Long testId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(testId));
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> getPdf(@PathVariable Long id) {
        byte[] pdfBytes = service.getPdf(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "admit-card-" + id + ".pdf");
        return ResponseEntity.ok().headers(headers).body(pdfBytes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdmitCardResponse> update(@PathVariable Long id, @RequestBody AdmitCardRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
