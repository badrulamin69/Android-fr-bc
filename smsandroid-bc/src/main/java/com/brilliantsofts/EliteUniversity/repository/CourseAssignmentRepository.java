package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.CourseAssignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseAssignmentRepository extends JpaRepository<CourseAssignment, Long> {

    @Query("SELECT c FROM CourseAssignment c WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(c.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<CourseAssignment> findAllWithSearch(@Param("search") String search, Pageable pageable);
}