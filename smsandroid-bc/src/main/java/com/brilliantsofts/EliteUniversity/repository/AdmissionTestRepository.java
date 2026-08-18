package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionTestRepository extends JpaRepository<AdmissionTest, Long> {
    List<AdmissionTest> findByStatus(String status);
    List<AdmissionTest> findByAcademicYear(String academicYear);
    List<AdmissionTest> findBySessionId(Long sessionId);
    List<AdmissionTest> findByFacultyId(Long facultyId);
    List<AdmissionTest> findByDepartmentId(Long departmentId);
    List<AdmissionTest> findByProgramId(Long programId);
    List<AdmissionTest> findByTestType(String testType);
}
