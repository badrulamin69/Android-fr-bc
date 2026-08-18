package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ProgramSeatAllocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgramSeatAllocationRepository extends JpaRepository<ProgramSeatAllocation, Long> {

    @Query("SELECT p FROM ProgramSeatAllocation p WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(p.allocationNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<ProgramSeatAllocation> findAllWithSearch(@Param("search") String search, Pageable pageable);

    long countByConfigId(Long configId);
    long countByConfigIdAndStatus(Long configId, String status);
    long countByConfigIdAndIsWaiting(Long configId, boolean isWaiting);
}
