package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Applicant;
import com.brilliantsofts.EliteUniversity.enums.ApplicationLevel;
import com.brilliantsofts.EliteUniversity.enums.ApplicationStatus;

import java.util.List;

public interface ApplicantRepository extends org.springframework.data.jpa.repository.JpaRepository<Applicant, Long> {
    Applicant findByApplicationNumber(String applicationNumber);
    Applicant findByUserId(Long userId);
    List<Applicant> findByApplicationLevel(ApplicationLevel level);
    List<Applicant> findByStatus(ApplicationStatus status);
    List<Applicant> findByProgramId(Long programId);
    List<Applicant> findByApplicationNumberContainingIgnoreCaseOrFullNameContainingIgnoreCase(String applicationNumber, String fullName);
    org.springframework.data.domain.Page<Applicant> findByApplicationNumberContainingIgnoreCaseOrFullNameContainingIgnoreCase(String applicationNumber, String fullName, org.springframework.data.domain.Pageable pageable);
}
