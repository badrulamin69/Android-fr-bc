package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.UserRoleRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserRoleResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.UserRoleMapper;
import com.brilliantsofts.EliteUniversity.entity.UserRole;
import com.brilliantsofts.EliteUniversity.repository.RoleRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.repository.UserRoleRepository;
import com.brilliantsofts.EliteUniversity.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserRoleResponse create(UserRoleRequest request) {
        UserRole entity = UserRoleMapper.toEntity(request);
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getRoleId() != null) entity.setRole(roleRepository.findById(request.getRoleId()).orElse(null));
        return UserRoleMapper.toResponse(repository.save(entity));
    }
    @Override
    public UserRoleResponse update(Long id, UserRoleRequest request) {
        UserRole entity = repository.findById(id).orElseThrow(() -> new RuntimeException("UserRole not found"));
        if (request.getUserId() != null) entity.setUser(userRepository.findById(request.getUserId()).orElse(null));
        if (request.getRoleId() != null) entity.setRole(roleRepository.findById(request.getRoleId()).orElse(null));
        return UserRoleMapper.toResponse(repository.save(entity));
    }
    @Override
    public UserRoleResponse getById(Long id) {
        return UserRoleMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("UserRole not found")));
    }
    @Override
    public Page<UserRoleResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(UserRoleMapper::toResponse);
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
