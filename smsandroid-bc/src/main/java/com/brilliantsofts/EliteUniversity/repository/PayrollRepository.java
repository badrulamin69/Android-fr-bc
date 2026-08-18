package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Payroll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    @Query("SELECT p FROM Payroll p WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(p.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Payroll> findAllWithSearch(@Param("search") String search, Pageable pageable);
}