package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.DepartmentAllocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentAllocationRepository extends JpaRepository<DepartmentAllocation, Long> {

    @Query("SELECT d FROM DepartmentAllocation d WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(d.allocationNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<DepartmentAllocation> findAllWithSearch(@Param("search") String search, Pageable pageable);
}