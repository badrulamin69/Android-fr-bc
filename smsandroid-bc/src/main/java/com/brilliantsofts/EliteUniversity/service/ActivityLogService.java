package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.request.ActivityLogRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ActivityLogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ActivityLogService {
    ActivityLogResponse create(ActivityLogRequest request);
    ActivityLogResponse getById(Long id);
    Page<ActivityLogResponse> getAll(Pageable pageable);
    Page<ActivityLogResponse> getByUserId(Long userId, Pageable pageable);
    Page<ActivityLogResponse> getByModule(String module, Pageable pageable);
    List<ActivityLogResponse> getRecent(int limit);
    Map<String, Object> getStats();
    void delete(Long id);
}
