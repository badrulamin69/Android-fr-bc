package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.NotificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.NotificationResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.NotificationMapper;
import com.brilliantsofts.EliteUniversity.entity.Notification;
import com.brilliantsofts.EliteUniversity.repository.NotificationRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public NotificationResponse create(NotificationRequest request) {
        Notification entity = NotificationMapper.toEntity(request);
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        return NotificationMapper.toResponse(repository.save(entity));
    }
    @Override
    public NotificationResponse update(Long id, NotificationRequest request) {
        Notification entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
        entity.setTitle(request.getTitle());
        entity.setMessage(request.getMessage());
        entity.setType(request.getType());
        entity.setRead(Boolean.TRUE.equals(request.getIsRead()));
        entity.setReferenceType(request.getReferenceType());
        entity.setReferenceId(request.getReferenceId());
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        return NotificationMapper.toResponse(repository.save(entity));
    }
    @Override
    public NotificationResponse getById(Long id) {
        return NotificationMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found")));
    }
    @Override
    public Page<NotificationResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(NotificationMapper::toResponse);
    }
    @Override
    public long getUnreadCount(Long userId) {
        return repository.countByUserIdAndIsReadFalse(userId);
    }
    @Override
    public NotificationResponse markAsRead(Long id) {
        Notification entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
        entity.setRead(true);
        return NotificationMapper.toResponse(repository.save(entity));
    }
    @Override
    public void markAllAsRead(Long userId) {
        repository.findByUserId(userId).forEach(n -> {
            n.setRead(true);
            repository.save(n);
        });
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
