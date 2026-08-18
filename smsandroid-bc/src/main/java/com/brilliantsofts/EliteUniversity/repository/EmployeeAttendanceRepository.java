package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.EmployeeAttendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {

    @Query("SELECT a FROM EmployeeAttendance a WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.remarks) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<EmployeeAttendance> findAllWithSearch(@Param("search") String search, Pageable pageable);

    Page<EmployeeAttendance> findByEmployeeId(Long employeeId, Pageable pageable);
}
