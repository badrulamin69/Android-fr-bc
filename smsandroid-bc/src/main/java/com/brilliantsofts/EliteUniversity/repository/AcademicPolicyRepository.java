package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AcademicPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AcademicPolicyRepository extends JpaRepository<AcademicPolicy, Long> {

    @Query("SELECT p FROM AcademicPolicy p WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.policyType) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AcademicPolicy> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
