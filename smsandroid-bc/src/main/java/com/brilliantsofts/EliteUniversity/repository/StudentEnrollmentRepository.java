package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.StudentEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Long> {

    @Query("SELECT s FROM StudentEnrollment s WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(s.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<StudentEnrollment> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
