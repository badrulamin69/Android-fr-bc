package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.EligibilityCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EligibilityCriteriaRepository extends JpaRepository<EligibilityCriteria, Long> {

    @Query("SELECT e FROM EligibilityCriteria e WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(e.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<EligibilityCriteria> findAllWithSearch(@Param("search") String search, Pageable pageable);

    Page<EligibilityCriteria> findByStatus(String status, Pageable pageable);
}