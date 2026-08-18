package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionConfirmation;

import java.util.List;
import java.util.Optional;

public interface AdmissionConfirmationRepository extends org.springframework.data.jpa.repository.JpaRepository<AdmissionConfirmation, Long> {
    Optional<AdmissionConfirmation> findByConfirmationNumber(String confirmationNumber);
    Optional<AdmissionConfirmation> findByAllocationId(Long allocationId);
    List<AdmissionConfirmation> findByRegistrationId(Long registrationId);
    List<AdmissionConfirmation> findByStatus(String status);
    long countByStatus(String status);
    long countByDocumentsVerified(Boolean documentsVerified);
    long countByFeePaid(Boolean feePaid);
}
