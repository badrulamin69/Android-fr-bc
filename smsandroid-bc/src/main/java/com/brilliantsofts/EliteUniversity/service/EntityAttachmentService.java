package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.EntityAttachmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EntityAttachmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntityAttachmentService {
    EntityAttachmentResponse create(EntityAttachmentRequest request);
    EntityAttachmentResponse getById(Long id);
    Page<EntityAttachmentResponse> getByEntityTypeAndEntityId(String entityType, Long entityId, Pageable pageable);
    long countByEntityTypeAndEntityId(String entityType, Long entityId);
    void delete(Long id);
}