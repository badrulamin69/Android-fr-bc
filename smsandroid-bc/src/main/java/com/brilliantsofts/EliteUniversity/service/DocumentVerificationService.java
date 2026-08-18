package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.DocumentVerificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.DocumentVerificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentVerificationService {
    DocumentVerificationResponse create(DocumentVerificationRequest request);
    DocumentVerificationResponse update(Long id, DocumentVerificationRequest request);
    DocumentVerificationResponse getById(Long id);
    Page<DocumentVerificationResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}