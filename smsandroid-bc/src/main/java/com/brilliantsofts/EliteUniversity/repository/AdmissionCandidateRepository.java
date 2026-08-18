package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdmissionCandidateRepository extends JpaRepository<AdmissionCandidate, Long> {
    Optional<AdmissionCandidate> findByEmail(String email);
    Optional<AdmissionCandidate> findByUniqueCode(String uniqueCode);
    List<AdmissionCandidate> findByStatus(String status);
}
