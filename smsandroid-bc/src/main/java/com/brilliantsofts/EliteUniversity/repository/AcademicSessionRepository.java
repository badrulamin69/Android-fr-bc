package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AcademicSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicSessionRepository extends JpaRepository<AcademicSession, Long> {
    List<AcademicSession> findByActiveTrue();
}
