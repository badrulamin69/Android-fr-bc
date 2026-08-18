package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionFeeCollectionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionFeeCollectionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdmissionFeeCollectionService {
    AdmissionFeeCollectionResponse create(AdmissionFeeCollectionRequest request);
    AdmissionFeeCollectionResponse update(Long id, AdmissionFeeCollectionRequest request);
    AdmissionFeeCollectionResponse getById(Long id);
    Page<AdmissionFeeCollectionResponse> getAll(Pageable pageable, String search, String status);
    void delete(Long id);
}
