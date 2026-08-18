package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ApplicantChoiceSubmission;

import java.util.List;
import java.util.Optional;

public interface ApplicantChoiceSubmissionRepository extends org.springframework.data.jpa.repository.JpaRepository<ApplicantChoiceSubmission, Long> {
    Optional<ApplicantChoiceSubmission> findBySubmissionId(String submissionId);
    Optional<ApplicantChoiceSubmission> findByRegistrationIdAndConfigId(Long registrationId, Long configId);
    List<ApplicantChoiceSubmission> findByConfigId(Long configId);
    List<ApplicantChoiceSubmission> findByConfigIdAndStatus(Long configId, String status);
    long countByConfigId(Long configId);
    long countByConfigIdAndStatus(Long configId, String status);
}
