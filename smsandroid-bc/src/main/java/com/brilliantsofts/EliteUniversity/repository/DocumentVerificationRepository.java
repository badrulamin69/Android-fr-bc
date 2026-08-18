package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.DocumentVerification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DocumentVerificationRepository extends JpaRepository<DocumentVerification, Long> {

    @Query("SELECT d FROM DocumentVerification d WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(d.documentType) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.documentNumber) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<DocumentVerification> findAllWithSearch(@Param("search") String search, Pageable pageable);
}