package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ExaminationResult;

import java.util.List;

public interface ExaminationResultRepository extends org.springframework.data.jpa.repository.JpaRepository<ExaminationResult, Long> {
    List<ExaminationResult> findByStudentId(Long studentId);
    List<ExaminationResult> findByExaminationId(Long examinationId);
}
