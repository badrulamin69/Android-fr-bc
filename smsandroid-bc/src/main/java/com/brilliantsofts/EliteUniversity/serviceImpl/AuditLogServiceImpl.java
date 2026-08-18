package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.AuditLogRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AuditLogResponse;
import com.brilliantsofts.EliteUniversity.entity.AuditLog;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.dto.mapper.AuditLogMapper;
import com.brilliantsofts.EliteUniversity.repository.AuditLogRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl implements AuditLogService {
    @Autowired
    private AuditLogRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public AuditLogResponse create(AuditLogRequest request) {
        AuditLog entity = AuditLogMapper.toEntity(request);
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId()).orElse(null);
            entity.setUser(user);
        }
        return AuditLogMapper.toResponse(repository.save(entity));
    }

    @Override
    public AuditLogResponse update(Long id, AuditLogRequest request) {
        AuditLog entity = repository.findById(id).orElseThrow(() -> new RuntimeException("AuditLog not found"));
        entity.setAction(request.getAction());
        entity.setEntityType(request.getEntityType());
        entity.setEntityId(request.getEntityId());
        entity.setOldValue(request.getOldValue());
        entity.setNewValue(request.getNewValue());
        entity.setIpAddress(request.getIpAddress());
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId()).orElse(null);
            entity.setUser(user);
        }
        return AuditLogMapper.toResponse(repository.save(entity));
    }

    @Override
    public AuditLogResponse getById(Long id) {
        return AuditLogMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("AuditLog not found")));
    }

    @Override
    public Page<AuditLogResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(AuditLogMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
