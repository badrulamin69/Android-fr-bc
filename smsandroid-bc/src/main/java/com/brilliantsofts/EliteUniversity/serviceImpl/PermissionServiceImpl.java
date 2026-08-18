package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.PermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.PermissionResponse;
import com.brilliantsofts.EliteUniversity.entity.Permission;
import com.brilliantsofts.EliteUniversity.dto.mapper.PermissionMapper;
import com.brilliantsofts.EliteUniversity.repository.PermissionRepository;
import com.brilliantsofts.EliteUniversity.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository repository;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        return PermissionMapper.toResponse(repository.save(PermissionMapper.toEntity(request)));
    }

    @Override
    public PermissionResponse update(Long id, PermissionRequest request) {
        Permission entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setModule(request.getModule());
        entity.setAction(request.getAction());
        entity.setDescription(request.getDescription());
        return PermissionMapper.toResponse(repository.save(entity));
    }

    @Override
    public PermissionResponse getById(Long id) {
        return PermissionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Permission not found")));
    }

    @Override
    public Page<PermissionResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(PermissionMapper::toResponse);
    }

    @Override
    public List<PermissionResponse> getByModule(String module) {
        return repository.findByModule(module).stream().map(PermissionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
