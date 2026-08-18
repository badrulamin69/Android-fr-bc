package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.ActivityLogRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ActivityLogResponse;
import com.brilliantsofts.EliteUniversity.entity.ActivityLog;
import com.brilliantsofts.EliteUniversity.dto.mapper.ActivityLogMapper;
import com.brilliantsofts.EliteUniversity.repository.ActivityLogRepository;
import com.brilliantsofts.EliteUniversity.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    @Autowired
    private ActivityLogRepository repository;

    @Override
    public ActivityLogResponse create(ActivityLogRequest request) {
        ActivityLog entity = ActivityLogMapper.toEntity(request);
        return ActivityLogMapper.toResponse(repository.save(entity));
    }

    @Override
    public ActivityLogResponse getById(Long id) {
        return ActivityLogMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("ActivityLog not found")));
    }

    @Override
    public Page<ActivityLogResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(ActivityLogMapper::toResponse);
    }

    @Override
    public Page<ActivityLogResponse> getByUserId(Long userId, Pageable pageable) {
        return repository.findByUserId(userId, pageable).map(ActivityLogMapper::toResponse);
    }

    @Override
    public Page<ActivityLogResponse> getByModule(String module, Pageable pageable) {
        return repository.findByModule(module, pageable).map(ActivityLogMapper::toResponse);
    }

    @Override
    public List<ActivityLogResponse> getRecent(int limit) {
        return repository.findTop10ByOrderByCreatedAtDesc().stream()
                .limit(limit)
                .map(ActivityLogMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalLogs", repository.count());
        return stats;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
