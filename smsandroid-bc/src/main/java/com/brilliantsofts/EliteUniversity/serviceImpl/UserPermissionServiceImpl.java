package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.UserPermissionRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserPermissionResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.UserPermissionMapper;
import com.brilliantsofts.EliteUniversity.entity.UserPermission;
import com.brilliantsofts.EliteUniversity.repository.PermissionRepository;
import com.brilliantsofts.EliteUniversity.repository.UserPermissionRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {
    @Autowired
    private UserPermissionRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public UserPermissionResponse create(UserPermissionRequest request) {
        UserPermission entity = UserPermissionMapper.toEntity(request);
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getPermissionId() != null) entity.setPermission(permissionRepository.findById(request.getPermissionId()).orElse(null));
        return UserPermissionMapper.toResponse(repository.save(entity));
    }
    @Override
    public UserPermissionResponse update(Long id, UserPermissionRequest request) {
        UserPermission entity = repository.findById(id).orElseThrow(() -> new RuntimeException("UserPermission not found"));
        entity.setGranted(request.isGranted());
        entity.setNotes(request.getNotes());
        entity.setOverriddenById(request.getOverriddenById());
        entity.setExpiresAt(request.getExpiresAt());
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getPermissionId() != null) entity.setPermission(permissionRepository.findById(request.getPermissionId()).orElse(null));
        return UserPermissionMapper.toResponse(repository.save(entity));
    }
    @Override
    public UserPermissionResponse getById(Long id) {
        return UserPermissionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("UserPermission not found")));
    }
    @Override
    public Page<UserPermissionResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(UserPermissionMapper::toResponse);
    }
    @Override
    public List<UserPermissionResponse> getByUserId(Long userId) {
        return repository.findByUserId(userId).stream().map(UserPermissionMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
