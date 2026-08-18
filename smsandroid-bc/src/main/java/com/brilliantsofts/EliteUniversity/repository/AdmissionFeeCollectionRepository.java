package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionFeeCollection;

import java.util.List;
import java.util.Optional;

public interface AdmissionFeeCollectionRepository extends org.springframework.data.jpa.repository.JpaRepository<AdmissionFeeCollection, Long> {
    Optional<AdmissionFeeCollection> findByUniqueCode(String uniqueCode);
    Optional<AdmissionFeeCollection> findByTransactionId(String transactionId);
    List<AdmissionFeeCollection> findByCandidateId(Long candidateId);
    List<AdmissionFeeCollection> findByStatus(String status);
    long countByStatus(String status);
    long countByCandidateId(Long candidateId);
}
