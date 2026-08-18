package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdmissionRequirementRepository extends JpaRepository<AdmissionRequirement, Long> {

    @Query("SELECT a FROM AdmissionRequirement a WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.requirementType) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdmissionRequirement> findAllWithSearch(@Param("search") String search, Pageable pageable);

    Page<AdmissionRequirement> findByStatus(String status, Pageable pageable);
}
