package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ReportTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReportTemplateRepository extends JpaRepository<ReportTemplate, Long> {

    @Query("SELECT r FROM ReportTemplate r WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(r.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(r.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<ReportTemplate> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
