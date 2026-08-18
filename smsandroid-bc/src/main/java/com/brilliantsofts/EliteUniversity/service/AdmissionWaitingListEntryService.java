package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AdmissionWaitingListEntryRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AdmissionWaitingListEntryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdmissionWaitingListEntryService {
    AdmissionWaitingListEntryResponse create(Long waitingListId, AdmissionWaitingListEntryRequest request);
    AdmissionWaitingListEntryResponse update(Long entryId, AdmissionWaitingListEntryRequest request);
    AdmissionWaitingListEntryResponse getById(Long entryId);
    Page<AdmissionWaitingListEntryResponse> getByWaitingListId(Long waitingListId, Pageable pageable);
    List<AdmissionWaitingListEntryResponse> getAllByWaitingListId(Long waitingListId);
    AdmissionWaitingListEntryResponse updateStatus(Long entryId, String status);
    void delete(Long entryId);
}
