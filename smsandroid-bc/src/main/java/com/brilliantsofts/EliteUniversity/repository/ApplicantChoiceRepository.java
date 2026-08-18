package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ApplicantChoice;

import java.util.List;
import java.util.Optional;

public interface ApplicantChoiceRepository extends org.springframework.data.jpa.repository.JpaRepository<ApplicantChoice, Long> {
    List<ApplicantChoice> findBySubmissionIdOrderByPriorityAsc(Long submissionId);
    Optional<ApplicantChoice> findBySubmissionIdAndProgramId(Long submissionId, Long programId);
    long countBySubmissionId(Long submissionId);
    void deleteBySubmissionId(Long submissionId);
}
