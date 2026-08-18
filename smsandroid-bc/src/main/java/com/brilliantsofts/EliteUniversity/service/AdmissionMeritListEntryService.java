package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionMeritListEntryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionMeritListEntryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmissionMeritListEntryService {
    AdmissionMeritListEntryResponse create(Long meritListId, AdmissionMeritListEntryRequest request);
    AdmissionMeritListEntryResponse update(Long entryId, AdmissionMeritListEntryRequest request);
    AdmissionMeritListEntryResponse getById(Long entryId);
    Page<AdmissionMeritListEntryResponse> getByMeritListId(Long meritListId, Pageable pageable);
    Page<AdmissionMeritListEntryResponse> getByMeritListIdWithFilter(Long meritListId, String search, String status, Pageable pageable);
    List<AdmissionMeritListEntryResponse> getAllByMeritListId(Long meritListId);
    AdmissionMeritListEntryResponse updateStatus(Long entryId, String status);
    void delete(Long entryId);
}
