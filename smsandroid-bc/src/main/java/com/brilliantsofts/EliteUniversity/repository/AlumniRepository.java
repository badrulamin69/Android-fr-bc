package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Alumni;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
    Page<Alumni> findByStudentId(Long studentId, Pageable pageable);

    @Query("SELECT a FROM Alumni a WHERE " +
            "LOWER(a.degree) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.currentCompany) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.currentDesignation) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.currentLocation) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.email) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Alumni> search(@Param("search") String search, Pageable pageable);
}
