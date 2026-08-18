package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AcademicResult;
import com.brilliantsofts.EliteUniversity.enums.AcademicExamType;

import java.util.List;

public interface AcademicResultRepository extends org.springframework.data.jpa.repository.JpaRepository<AcademicResult, Long> {
    List<AcademicResult> findByApplicantId(Long applicantId);
    List<AcademicResult> findByExamType(AcademicExamType type);
    AcademicResult findByApplicantIdAndExamType(Long applicantId, AcademicExamType type);
}
