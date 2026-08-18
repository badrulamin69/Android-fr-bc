package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionMeritListRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionMeritListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AdmissionMeritListService {
    AdmissionMeritListResponse create(AdmissionMeritListRequest request);
    AdmissionMeritListResponse update(Long id, AdmissionMeritListRequest request);
    AdmissionMeritListResponse getById(Long id);
    Page<AdmissionMeritListResponse> getAll(Pageable pageable);
    void delete(Long id);
    AdmissionMeritListResponse generate(Long testId, String listName, Integer totalSeats);
    AdmissionMeritListResponse generateByCircular(Long circularId, String listName, Integer totalSeats, Double cutoffScore);
    AdmissionMeritListResponse publish(Long id);
    AdmissionMeritListResponse publishByCircular(Long circularId);
    java.util.List<AdmissionMeritListResponse> getByCircularId(Long circularId);
    AdmissionMeritListResponse unpublish(Long id);
    AdmissionMeritListResponse archive(Long id);
    Map<String, Object> getStats();
}
