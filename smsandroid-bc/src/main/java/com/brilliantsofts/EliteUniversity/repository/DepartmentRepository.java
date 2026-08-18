package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Department;
import java.util.Optional;
import java.util.List;

public interface DepartmentRepository extends org.springframework.data.jpa.repository.JpaRepository<Department, Long> {
    List<Department> findByFacultyId(Long facultyId);
    Optional<Department> findByCode(String code);
}
