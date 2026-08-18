package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionCircular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionCircularRepository extends JpaRepository<AdmissionCircular, Long> {
    List<AdmissionCircular> findByIsPublished(Boolean isPublished);
    List<AdmissionCircular> findByStatus(String status);
    List<AdmissionCircular> findBySessionId(Long sessionId);
    List<AdmissionCircular> findByProgramId(Long programId);
}
