package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.EntityAttachmentMapper;
import com.brilliantsofts.EliteUniversity.dto.request.EntityAttachmentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EntityAttachmentResponse;
import com.brilliantsofts.EliteUniversity.entity.EntityAttachment;
import com.brilliantsofts.EliteUniversity.repository.EntityAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityAttachmentServiceImpl implements EntityAttachmentService {

    private final EntityAttachmentRepository repository;

    @Override
    public EntityAttachmentResponse create(EntityAttachmentRequest request) {
        EntityAttachment entity = EntityAttachmentMapper.toEntity(request);
        return EntityAttachmentMapper.toResponse(repository.save(entity));
    }

    @Override
    public EntityAttachmentResponse getById(Long id) {
        EntityAttachment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found with id: " + id));
        return EntityAttachmentMapper.toResponse(entity);
    }

    @Override
    public Page<EntityAttachmentResponse> getByEntityTypeAndEntityId(String entityType, Long entityId, Pageable pageable) {
        return repository.findByEntityTypeAndEntityId(entityType, entityId, pageable)
                .map(EntityAttachmentMapper::toResponse);
    }

    @Override
    public long countByEntityTypeAndEntityId(String entityType, Long entityId) {
        return repository.countByEntityTypeAndEntityId(entityType, entityId);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Attachment not found with id: " + id);
        }
        repository.deleteById(id);
    }
}