package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdministrationDivisionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdministrationDivisionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdministrationDivisionService {
    AdministrationDivisionResponse create(AdministrationDivisionRequest request);
    AdministrationDivisionResponse update(Long id, AdministrationDivisionRequest request);
    AdministrationDivisionResponse getById(Long id);
    Page<AdministrationDivisionResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
