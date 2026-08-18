package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    Student findByStudentId(String studentId);
    Student findByUserId(Long userId);
    Student findByApplicantId(Long applicantId);
    List<Student> findByProgramId(Long programId);
    List<Student> findByAcademicSessionId(Long academicSessionId);
}
