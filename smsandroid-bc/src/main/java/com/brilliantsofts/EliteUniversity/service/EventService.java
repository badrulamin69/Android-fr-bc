package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EventRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {
    EventResponse create(EventRequest request);
    EventResponse update(Long id, EventRequest request);
    EventResponse getById(Long id);
    Page<EventResponse> getAll(Pageable pageable);
    void delete(Long id);
}
