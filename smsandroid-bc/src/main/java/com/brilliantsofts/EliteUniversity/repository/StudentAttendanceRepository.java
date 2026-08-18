package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.StudentAttendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {
    Page<StudentAttendance> findByStudentId(Long studentId, Pageable pageable);

    Page<StudentAttendance> findByCourseId(Long courseId, Pageable pageable);

    @Query("SELECT a FROM StudentAttendance a WHERE " +
            "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.remarks) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<StudentAttendance> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
