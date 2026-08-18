package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.GeneratedReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GeneratedReportRepository extends JpaRepository<GeneratedReport, Long> {

    @Query("SELECT g FROM GeneratedReport g WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(g.title) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(g.reportType) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<GeneratedReport> findAllWithSearch(@Param("search") String search, Pageable pageable);
}