package com.brilliantsofts.EliteUniversity.serviceImpl;

import com.brilliantsofts.EliteUniversity.dto.response.LoginSessionResponse;
import com.brilliantsofts.EliteUniversity.dto.mapper.LoginSessionMapper;
import com.brilliantsofts.EliteUniversity.entity.LoginSession;
import com.brilliantsofts.EliteUniversity.repository.LoginSessionRepository;
import com.brilliantsofts.EliteUniversity.service.LoginSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginSessionServiceImpl implements LoginSessionService {
    @Autowired
    private LoginSessionRepository repository;

    @Override
    public LoginSessionResponse getById(Long id) {
        return LoginSessionMapper.toResponse(repository.findById(id).orElseThrow(() -> new RuntimeException("LoginSession not found")));
    }
    @Override
    public Page<LoginSessionResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(LoginSessionMapper::toResponse);
    }
    @Override
    public List<LoginSessionResponse> getActiveSessions() {
        return repository.findByIsActiveTrue().stream().map(LoginSessionMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public long getActiveSessionCount() {
        return repository.countByIsActiveTrue();
    }
    @Override
    public List<LoginSessionResponse> getByUserId(Long userId) {
        return repository.findByUserId(userId).stream().map(LoginSessionMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void terminateSession(Long id) {
        LoginSession entity = repository.findById(id).orElseThrow(() -> new RuntimeException("LoginSession not found"));
        entity.setActive(false);
        entity.setExpired(true);
        entity.setLogoutTime(LocalDateTime.now());
        repository.save(entity);
    }
    @Override
    public void terminateAllUserSessions(Long userId) {
        repository.findByUserId(userId).stream()
                .filter(LoginSession::isActive)
                .forEach(session -> {
                    session.setActive(false);
                    session.setExpired(true);
                    session.setLogoutTime(LocalDateTime.now());
                    repository.save(session);
                });
    }
    @Override
    public void delete(Long id) { repository.deleteById(id); }
}
