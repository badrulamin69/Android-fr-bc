package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdministrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdministrationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdministrationService {
    AdministrationResponse create(AdministrationRequest request);
    AdministrationResponse update(Long id, AdministrationRequest request);
    AdministrationResponse getById(Long id);
    Page<AdministrationResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
