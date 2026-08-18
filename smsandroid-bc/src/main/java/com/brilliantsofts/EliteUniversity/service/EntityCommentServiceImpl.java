package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.EntityCommentMapper;
import com.brilliantsofts.EliteUniversity.dto.request.EntityCommentRequest;
import com.brilliantsofts.EliteUniversity.dto.response.EntityCommentResponse;
import com.brilliantsofts.EliteUniversity.entity.EntityComment;
import com.brilliantsofts.EliteUniversity.repository.EntityCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntityCommentServiceImpl implements EntityCommentService {

    private final EntityCommentRepository repository;

    @Override
    public EntityCommentResponse create(EntityCommentRequest request) {
        EntityComment entity = EntityCommentMapper.toEntity(request);
        return EntityCommentMapper.toResponse(repository.save(entity));
    }

    @Override
    public EntityCommentResponse update(Long id, String content) {
        EntityComment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        entity.setContent(content);
        entity.setEdited(true);
        return EntityCommentMapper.toResponse(repository.save(entity));
    }

    @Override
    public EntityCommentResponse getById(Long id) {
        EntityComment entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        return EntityCommentMapper.toResponse(entity);
    }

    @Override
    public Page<EntityCommentResponse> getByEntityTypeAndEntityId(String entityType, Long entityId, Pageable pageable) {
        return repository.findByEntityTypeAndEntityId(entityType, entityId, pageable)
                .map(EntityCommentMapper::toResponse);
    }

    @Override
    public long countByEntityTypeAndEntityId(String entityType, Long entityId) {
        return repository.countByEntityTypeAndEntityId(entityType, entityId);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Comment not found with id: " + id);
        }
        repository.deleteById(id);
    }
}