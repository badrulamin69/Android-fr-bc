package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.RoleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.RoleResponse;
import com.brilliantsofts.EliteUniversity.entity.Permission;
import com.brilliantsofts.EliteUniversity.entity.Role;
import com.brilliantsofts.EliteUniversity.dto.mapper.RoleMapper;
import com.brilliantsofts.EliteUniversity.repository.PermissionRepository;
import com.brilliantsofts.EliteUniversity.repository.RoleRepository;
import com.brilliantsofts.EliteUniversity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public RoleResponse create(RoleRequest request) {
        Role entity = RoleMapper.toEntity(request);
        if (request.getPermissionIds() != null && !request.getPermissionIds().isEmpty()) {
            List<Permission> permissions = permissionRepository.findAllById(request.getPermissionIds());
            entity.setPermissions(permissions);
        }
        return RoleMapper.toResponse(repository.save(entity));
    }

    @Override
    public RoleResponse update(Long id, RoleRequest request) {
        Role entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        entity.setName(request.getName());
        entity.setCode(request.getCode());
        entity.setDescription(request.getDescription());
        entity.setActive(request.isActive());
        if (request.getPermissionIds() != null) {
            List<Permission> permissions = permissionRepository.findAllById(request.getPermissionIds());
            entity.setPermissions(permissions);
        }
        return RoleMapper.toResponse(repository.save(entity));
    }

    @Override
    public RoleResponse getById(Long id) {
        return RoleMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("Role not found")));
    }

    @Override
    public Page<RoleResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(RoleMapper::toResponse);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
