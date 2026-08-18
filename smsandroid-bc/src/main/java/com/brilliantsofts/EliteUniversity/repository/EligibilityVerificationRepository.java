package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.EligibilityVerification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EligibilityVerificationRepository extends JpaRepository<EligibilityVerification, Long> {

    @Query("SELECT e FROM EligibilityVerification e WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(e.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<EligibilityVerification> findAllWithSearch(@Param("search") String search, Pageable pageable);

    List<EligibilityVerification> findByTestId(Long testId);

    @Query("SELECT COUNT(e) FROM EligibilityVerification e WHERE e.testId = :testId AND e.status = :status")
    long countByTestIdAndStatus(@Param("testId") Long testId, @Param("status") String status);
}