package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.LeaveRequestRequest;
import com.brilliantsofts.EliteUniversity.dto.response.LeaveRequestResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeaveRequestService {
    LeaveRequestResponse create(LeaveRequestRequest request);
    LeaveRequestResponse update(Long id, LeaveRequestRequest request);
    LeaveRequestResponse getById(Long id);
    Page<LeaveRequestResponse> getAll(String search, Pageable pageable);
    void delete(Long id);
}
