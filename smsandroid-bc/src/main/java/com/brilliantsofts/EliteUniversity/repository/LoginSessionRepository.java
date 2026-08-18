package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoginSessionRepository extends JpaRepository<LoginSession, Long> {
    List<LoginSession> findByIsActiveTrue();
    long countByIsActiveTrue();
    List<LoginSession> findByUserId(Long userId);
    Optional<LoginSession> findBySessionToken(String sessionToken);
}
