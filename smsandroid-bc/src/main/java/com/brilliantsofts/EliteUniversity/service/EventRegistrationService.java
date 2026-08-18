package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EventRegistrationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EventRegistrationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRegistrationService {
    EventRegistrationResponse create(EventRegistrationRequest request);
    EventRegistrationResponse update(Long id, EventRegistrationRequest request);
    EventRegistrationResponse getById(Long id);
    Page<EventRegistrationResponse> getAll(Pageable pageable);
    void delete(Long id);
}
