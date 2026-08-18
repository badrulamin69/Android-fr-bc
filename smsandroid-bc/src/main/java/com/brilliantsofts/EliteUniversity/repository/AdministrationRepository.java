package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Administration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministrationRepository extends JpaRepository<Administration, Long> {

    @Query("SELECT a FROM Administration a WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(a.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.employeeCode) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Administration> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
