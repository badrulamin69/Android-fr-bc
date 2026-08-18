package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.request.UserRequest;
import com.brilliantsofts.EliteUniversity.dto.response.UserResponse;
import com.brilliantsofts.EliteUniversity.entity.User;
import com.brilliantsofts.EliteUniversity.dto.mapper.UserMapper;
import com.brilliantsofts.EliteUniversity.repository.UserRepository;
import com.brilliantsofts.EliteUniversity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(UserRequest request) {
        User entity = UserMapper.toEntity(request);
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            entity.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return UserMapper.toResponse(repository.save(entity));
    }
    @Override
    public UserResponse update(Long id, UserRequest request) {
        User entity = repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        entity.setUsername(request.getUsername());
        entity.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isEmpty() && !request.getPassword().startsWith("$2a$")) {
            entity.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        entity.setPhone(request.getPhone());
        entity.setEnabled(request.isEnabled());
        entity.setAccountNonLocked(request.isAccountNonLocked());
        entity.setAccountNonExpired(request.isAccountNonExpired());
        entity.setCredentialsNonExpired(request.isCredentialsNonExpired());
        entity.setRole(request.getRole());
        return UserMapper.toResponse(repository.save(entity));
    }
    @Override
    public UserResponse getById(Long id) {
        return UserMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }
    @Override
    public UserResponse getByUsername(String username) {
        return UserMapper.toResponse(repository.findByUsername(username).orElse(null));
    }
    @Override
    public UserResponse getByEmail(String email) {
        return UserMapper.toResponse(repository.findByEmail(email).orElse(null));
    }
    @Override
    public UserResponse getByUsernameOrEmail(String value) {
        return UserMapper.toResponse(repository.findByUsernameOrEmail(value, value).orElse(null));
    }
    @Override
    public org.springframework.data.domain.Page<UserResponse> getAll(org.springframework.data.domain.Pageable pageable) {
        return repository.findAll(pageable).map(UserMapper::toResponse);
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
    @Override
    public boolean existsByUsername(String username) { return repository.existsByUsername(username); }
    @Override
    public boolean existsByEmail(String email) { return repository.existsByEmail(email); }
}
