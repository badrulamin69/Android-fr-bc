package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.NotificationRequest;
import com.brilliantsofts.EliteUniversity.dto.response.NotificationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {
    NotificationResponse create(NotificationRequest request);
    NotificationResponse update(Long id, NotificationRequest request);
    NotificationResponse getById(Long id);
    Page<NotificationResponse> getAll(Pageable pageable);
    long getUnreadCount(Long userId);
    NotificationResponse markAsRead(Long id);
    void markAllAsRead(Long userId);
    void delete(Long id);
}
