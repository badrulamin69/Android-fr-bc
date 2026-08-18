package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionTestResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionTestResultRepository extends JpaRepository<AdmissionTestResult, Long> {
    List<AdmissionTestResult> findByTestId(Long testId);
    List<AdmissionTestResult> findByRegistrationId(Long registrationId);
    List<AdmissionTestResult> findByTestIdAndRegistrationId(Long testId, Long registrationId);
    Page<AdmissionTestResult> findByRegistrationIdIn(List<Long> registrationIds, Pageable pageable);
}
