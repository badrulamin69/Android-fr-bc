package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.ChoiceFillingConfigRequest;
import com.brilliantsofts.EliteUniversity.dto.response.ChoiceFillingConfigResponse;
import com.brilliantsofts.EliteUniversity.entity.ChoiceFillingConfig;
import com.brilliantsofts.EliteUniversity.dto.mapper.ChoiceFillingConfigMapper;
import com.brilliantsofts.EliteUniversity.repository.ChoiceFillingConfigRepository;
import com.brilliantsofts.EliteUniversity.service.ChoiceFillingConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChoiceFillingConfigServiceImpl implements ChoiceFillingConfigService {
    @Autowired
    private ChoiceFillingConfigRepository repository;

    @Override
    public ChoiceFillingConfigResponse create(ChoiceFillingConfigRequest request) {
        ChoiceFillingConfig entity = ChoiceFillingConfigMapper.toEntity(request);
        return ChoiceFillingConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public ChoiceFillingConfigResponse update(Long id, ChoiceFillingConfigRequest request) {
        ChoiceFillingConfig entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ChoiceFillingConfig not found"));
        entity.setSessionId(request.getSessionId());
        entity.setChoiceStartDate(request.getChoiceStartDate());
        entity.setChoiceEndDate(request.getChoiceEndDate());
        entity.setMaxChoices(request.getMaxChoices());
        entity.setMinChoices(request.getMinChoices());
        entity.setAllowEditingBeforeDeadline(request.getAllowEditingBeforeDeadline());
        entity.setAutoLockAfterDeadline(request.getAutoLockAfterDeadline());
        entity.setIncludeWaitingList(request.getIncludeWaitingList());
        entity.setStatus(request.getStatus());
        entity.setRemarks(request.getRemarks());
        entity.setIsActive(request.getIsActive());
        return ChoiceFillingConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public ChoiceFillingConfigResponse getById(Long id) {
        return ChoiceFillingConfigMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("ChoiceFillingConfig not found")));
    }

    @Override
    public Page<ChoiceFillingConfigResponse> getAll(Pageable pageable, String search, String status) {
        return repository.findAll(pageable).map(ChoiceFillingConfigMapper::toResponse);
    }

    @Override
    public ChoiceFillingConfigResponse activate(Long id) {
        List<ChoiceFillingConfig> activeConfigs = repository.findByIsActiveTrue();
        for (ChoiceFillingConfig active : activeConfigs) {
            active.setIsActive(false);
            repository.save(active);
        }
        ChoiceFillingConfig entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ChoiceFillingConfig not found"));
        entity.setIsActive(true);
        entity.setStatus("ACTIVE");
        return ChoiceFillingConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public ChoiceFillingConfigResponse close(Long id) {
        ChoiceFillingConfig entity = repository.findById(id).orElseThrow(() -> new RuntimeException("ChoiceFillingConfig not found"));
        entity.setIsActive(false);
        entity.setStatus("CLOSED");
        return ChoiceFillingConfigMapper.toResponse(repository.save(entity));
    }

    @Override
    public ChoiceFillingConfigResponse getActiveConfig() {
        ChoiceFillingConfig entity = repository.findTopByIsActiveTrueOrderByIdDesc().orElseGet(() -> {
            ChoiceFillingConfig def = new ChoiceFillingConfig();
            def.setSessionId(1L);
            def.setChoiceStartDate(LocalDateTime.now().minusDays(10));
            def.setChoiceEndDate(LocalDateTime.now().plusDays(60));
            def.setMinChoices(1);
            def.setMaxChoices(5);
            def.setAllowEditingBeforeDeadline(true);
            def.setAutoLockAfterDeadline(true);
            def.setIncludeWaitingList(true);
            def.setStatus("ACTIVE");
            def.setIsActive(true);
            def.setRemarks("General Admission Choice Filling Window");
            return repository.save(def);
        });
        return ChoiceFillingConfigMapper.toResponse(entity);
    }

    @Override
    public Map<String, Object> getStats() {
        Map<String, Object> stats = new HashMap<>();
        List<ChoiceFillingConfig> all = repository.findAll();
        long totalConfigs = all.size();
        long activeConfigs = all.stream().filter(c -> Boolean.TRUE.equals(c.getIsActive())).count();
        long closedConfigs = all.stream().filter(c -> "CLOSED".equals(c.getStatus())).count();
        stats.put("totalConfigs", totalConfigs);
        stats.put("activeConfigs", activeConfigs);
        stats.put("closedConfigs", closedConfigs);
        return stats;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
