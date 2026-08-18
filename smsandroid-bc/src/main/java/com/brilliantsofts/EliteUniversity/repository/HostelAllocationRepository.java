package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.HostelAllocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HostelAllocationRepository extends JpaRepository<HostelAllocation, Long> {

    @Query("SELECT ha FROM HostelAllocation ha WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(ha.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(ha.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<HostelAllocation> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
