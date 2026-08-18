package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionTestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionTestQuestionRepository extends JpaRepository<AdmissionTestQuestion, Long> {
    List<AdmissionTestQuestion> findByTestId(Long testId);
    long countByTestId(Long testId);
    List<AdmissionTestQuestion> findByTestIdAndIsActive(Long testId, Boolean isActive);
}
