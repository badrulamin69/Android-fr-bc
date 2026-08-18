package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdmissionApplicationRepository extends JpaRepository<AdmissionApplication, Long> {
    List<AdmissionApplication> findByStatus(String status);
    List<AdmissionApplication> findByCandidateId(Long candidateId);
    List<AdmissionApplication> findByCircularId(Long circularId);
    List<AdmissionApplication> findByCircularIdAndStatus(Long circularId, String status);
    List<AdmissionApplication> findBySessionId(Long sessionId);
    List<AdmissionApplication> findByIsVerified(Boolean isVerified);
    Optional<AdmissionApplication> findByUniqueCode(String uniqueCode);
    Optional<AdmissionApplication> findByApplicationNumber(String applicationNumber);
    List<AdmissionApplication> findByCandidateIdAndSessionId(Long candidateId, Long sessionId);
}
