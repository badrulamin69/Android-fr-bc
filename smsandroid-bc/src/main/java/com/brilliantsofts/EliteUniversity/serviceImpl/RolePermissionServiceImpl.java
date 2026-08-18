package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.RolePermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RolePermissionResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.RolePermissionMapper;
import com.brilliantsofts.EliteUniversity.entity.RolePermission;
import com.brilliantsofts.EliteUniversity.repository.PermissionRepository;
import com.brilliantsofts.EliteUniversity.repository.RolePermissionRepository;
import com.brilliantsofts.EliteUniversity.repository.RoleRepository;
import com.brilliantsofts.EliteUniversity.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionRepository repository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public RolePermissionResponse create(RolePermissionRequest request) {
        RolePermission entity = RolePermissionMapper.toEntity(request);
        if (request.getRoleId() != null) entity.setRole(roleRepository.findById(request.getRoleId()).orElse(null));
        if (request.getPermissionId() != null) entity.setPermission(permissionRepository.findById(request.getPermissionId()).orElse(null));
        return RolePermissionMapper.toResponse(repository.save(entity));
    }
    @Override
    public RolePermissionResponse update(Long id, RolePermissionRequest request) {
        RolePermission entity = repository.findById(id).orElseThrow(() -> new RuntimeException("RolePermission not found"));
        if (request.getRoleId() != null) entity.setRole(roleRepository.findById(request.getRoleId()).orElse(null));
        if (request.getPermissionId() != null) entity.setPermission(permissionRepository.findById(request.getPermissionId()).orElse(null));
        return RolePermissionMapper.toResponse(repository.save(entity));
    }
    @Override
    public RolePermissionResponse getById(Long id) {
        return RolePermissionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("RolePermission not found")));
    }
    @Override
    public Page<RolePermissionResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(RolePermissionMapper::toResponse);
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
