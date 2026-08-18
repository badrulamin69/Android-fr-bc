package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.CertificateRequest;
import com.brilliantsofts.EliteUniversity.dto.response.CertificateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CertificateService {
    CertificateResponse create(CertificateRequest request);
    CertificateResponse update(Long id, CertificateRequest request);
    CertificateResponse getById(Long id);
    Page<CertificateResponse> getAll(Pageable pageable, String search);
    void delete(Long id);
    Map<String, Object> getStats();
}
