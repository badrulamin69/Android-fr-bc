package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.mapper.SystemSettingMapper;
import com.brilliantsofts.EliteUniversity.dto.request.SystemSettingRequest;
import com.brilliantsofts.EliteUniversity.dto.response.SystemSettingResponse;
import com.brilliantsofts.EliteUniversity.entity.SystemSetting;
import com.brilliantsofts.EliteUniversity.repository.SystemSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SystemSettingServiceImpl implements SystemSettingService {

    private final SystemSettingRepository repository;

    @Override
    public SystemSettingResponse create(SystemSettingRequest request) {
        SystemSetting entity = SystemSettingMapper.toEntity(request);
        return SystemSettingMapper.toResponse(repository.save(entity));
    }

    @Override
    public SystemSettingResponse update(Long id, SystemSettingRequest request) {
        SystemSetting entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SystemSetting not found with id: " + id));
        entity.setSettingKey(request.getSettingKey());
        entity.setSettingValue(request.getSettingValue());
        entity.setSettingModule(request.getSettingModule());
        entity.setDescription(request.getDescription());
        entity.setDataType(request.getDataType());
        entity.setPublic(request.isPublic());
        return SystemSettingMapper.toResponse(repository.save(entity));
    }

    @Override
    public SystemSettingResponse getById(Long id) {
        SystemSetting entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SystemSetting not found with id: " + id));
        return SystemSettingMapper.toResponse(entity);
    }

    @Override
    public List<SystemSettingResponse> getAll() {
        return repository.findAll().stream()
                .map(SystemSettingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<SystemSettingResponse> getByModule(String module) {
        return repository.findBySettingModule(module).stream()
                .map(SystemSettingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SystemSettingResponse getByKey(String key) {
        SystemSetting entity = repository.findBySettingKey(key)
                .orElseThrow(() -> new RuntimeException("SystemSetting not found with key: " + key));
        return SystemSettingMapper.toResponse(entity);
    }

    @Override
    public List<SystemSettingResponse> getPublic() {
        return repository.findByIsPublicTrue().stream()
                .map(SystemSettingMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void batchUpdate(List<SystemSettingRequest> settings) {
        for (SystemSettingRequest request : settings) {
            repository.findBySettingKey(request.getSettingKey()).ifPresent(entity -> {
                entity.setSettingValue(request.getSettingValue());
                repository.save(entity);
            });
        }
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("SystemSetting not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteByKey(String key) {
        repository.findBySettingKey(key)
                .orElseThrow(() -> new RuntimeException("SystemSetting not found with key: " + key));
        repository.deleteBySettingKey(key);
    }
}
