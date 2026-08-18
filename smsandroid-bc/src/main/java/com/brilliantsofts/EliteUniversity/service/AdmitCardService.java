package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmitCardRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmitCardResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdmitCardService {
    AdmitCardResponse create(AdmitCardRequest request);
    AdmitCardResponse update(Long id, AdmitCardRequest request);
    AdmitCardResponse getById(Long id);
    Page<AdmitCardResponse> getAll(int page, int size, String sortBy, String sortDir, String search);
    void delete(Long id);
    List<AdmitCardResponse> findByTestId(Long testId);
    List<AdmitCardResponse> findByRegistrationId(Long registrationId);
    AdmitCardResponse generate(Long testId);
    byte[] getPdf(Long id);
}
