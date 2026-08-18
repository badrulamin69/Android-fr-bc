package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionWaitingListRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionWaitingListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdmissionWaitingListService {
    AdmissionWaitingListResponse create(AdmissionWaitingListRequest request);
    AdmissionWaitingListResponse update(Long id, AdmissionWaitingListRequest request);
    AdmissionWaitingListResponse getById(Long id);
    Page<AdmissionWaitingListResponse> getAll(Pageable pageable);
    void delete(Long id);
    AdmissionWaitingListResponse generate(Long testId, String name, Integer totalSlots);
    AdmissionWaitingListResponse publish(Long id);
    AdmissionWaitingListResponse unpublish(Long id);
    Map<String, Object> getStats();
}
