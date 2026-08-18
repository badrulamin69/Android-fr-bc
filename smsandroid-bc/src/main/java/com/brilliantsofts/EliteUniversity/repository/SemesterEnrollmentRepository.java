package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.SemesterEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SemesterEnrollmentRepository extends JpaRepository<SemesterEnrollment, Long> {

    @Query("SELECT s FROM SemesterEnrollment s WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(s.enrollmentNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<SemesterEnrollment> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
