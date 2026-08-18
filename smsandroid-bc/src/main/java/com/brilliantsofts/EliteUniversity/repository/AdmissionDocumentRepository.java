package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionDocument;

import java.util.List;

public interface AdmissionDocumentRepository extends org.springframework.data.jpa.repository.JpaRepository<AdmissionDocument, Long> {
    List<AdmissionDocument> findByConfirmationId(Long confirmationId);
    List<AdmissionDocument> findByConfirmationIdAndStatus(Long confirmationId, String status);
    long countByConfirmationId(Long confirmationId);
}
