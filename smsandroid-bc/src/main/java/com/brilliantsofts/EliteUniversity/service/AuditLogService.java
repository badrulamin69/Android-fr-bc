package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.AuditLogRequest;
import com.brilliantsofts.EliteUniversity.dto.response.AuditLogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuditLogService {
    AuditLogResponse create(AuditLogRequest request);
    AuditLogResponse update(Long id, AuditLogRequest request);
    AuditLogResponse getById(Long id);
    Page<AuditLogResponse> getAll(Pageable pageable);
    void delete(Long id);
}
