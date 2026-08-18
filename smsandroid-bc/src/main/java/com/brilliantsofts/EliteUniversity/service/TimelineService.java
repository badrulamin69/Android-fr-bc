package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.response.TimelineEventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TimelineService {
    Page<TimelineEventResponse> getByEntityTypeAndEntityId(String entityType, Long entityId, Pageable pageable);
    long countByEntityTypeAndEntityId(String entityType, Long entityId);
}
