package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Program;

import java.util.List;

public interface ProgramRepository extends org.springframework.data.jpa.repository.JpaRepository<Program, Long> {
    List<Program> findByDepartmentId(Long departmentId);
}
