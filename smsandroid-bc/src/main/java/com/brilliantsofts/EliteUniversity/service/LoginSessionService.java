package com.brilliantsofts.EliteUniversity.service;

import com.brilliantsofts.EliteUniversity.dto.response.LoginSessionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoginSessionService {
    LoginSessionResponse getById(Long id);
    Page<LoginSessionResponse> getAll(Pageable pageable);
    List<LoginSessionResponse> getActiveSessions();
    long getActiveSessionCount();
    List<LoginSessionResponse> getByUserId(Long userId);
    void terminateSession(Long id);
    void terminateAllUserSessions(Long userId);
    void delete(Long id);
}
