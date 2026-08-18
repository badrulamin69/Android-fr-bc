package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EntityCommentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EntityCommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntityCommentService {
    EntityCommentResponse create(EntityCommentRequest request);
    EntityCommentResponse update(Long id, String content);
    EntityCommentResponse getById(Long id);
    Page<EntityCommentResponse> getByEntityTypeAndEntityId(String entityType, Long entityId, Pageable pageable);
    long countByEntityTypeAndEntityId(String entityType, Long entityId);
    void delete(Long id);
}