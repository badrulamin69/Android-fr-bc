package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    Page<Certificate> findByStudentId(Long studentId, Pageable pageable);

    Page<Certificate> findByCertificateType(String certificateType, Pageable pageable);

    @Query("SELECT c FROM Certificate c WHERE " +
            "LOWER(c.certificateType) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.certificateNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(c.purpose) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Certificate> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
