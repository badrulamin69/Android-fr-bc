package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.TimelineEventMapper;
import com.brilliantsofts.EliteUniversity.dto.response.TimelineEventResponse;
import com.brilliantsofts.EliteUniversity.repository.TimelineEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineServiceImpl implements TimelineService {

    private final TimelineEventRepository repository;

    @Override
    public Page<TimelineEventResponse> getByEntityTypeAndEntityId(String entityType, Long entityId, Pageable pageable) {
        return repository.findByEntityTypeAndEntityIdOrderByCreatedAtDesc(entityType, entityId, pageable)
                .map(TimelineEventMapper::toResponse);
    }

    @Override
    public long countByEntityTypeAndEntityId(String entityType, Long entityId) {
        return repository.countByEntityTypeAndEntityId(entityType, entityId);
    }
}
