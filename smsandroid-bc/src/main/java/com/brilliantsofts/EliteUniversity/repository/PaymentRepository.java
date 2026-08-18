package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Payment;
import com.brilliantsofts.EliteUniversity.enums.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByTransactionId(String transactionId);
    List<Payment> findByApplicantId(Long applicantId);
    List<Payment> findByStudentId(Long studentId);
    Page<Payment> findByStudentId(Long studentId, Pageable pageable);
    Page<Payment> findByStatus(PaymentStatus status, Pageable pageable);
    List<Payment> findByStatus(PaymentStatus status);
    long countByStatus(PaymentStatus status);
}
