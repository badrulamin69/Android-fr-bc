package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.TransportAllocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransportAllocationRepository extends JpaRepository<TransportAllocation, Long> {

    @Query("SELECT ta FROM TransportAllocation ta WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(ta.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(ta.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<TransportAllocation> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
