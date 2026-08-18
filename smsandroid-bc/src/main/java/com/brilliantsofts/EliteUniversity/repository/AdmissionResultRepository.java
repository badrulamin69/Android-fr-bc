package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionResult;

import java.util.List;

public interface AdmissionResultRepository extends org.springframework.data.jpa.repository.JpaRepository<AdmissionResult, Long> {
    AdmissionResult findByApplicantId(Long applicantId);
    List<AdmissionResult> findByProgramId(Long programId);
    List<AdmissionResult> findByResultStatus(String resultStatus);
    List<AdmissionResult> findByProgramIdOrderByAdmissionScoreDesc(Long programId);
}
