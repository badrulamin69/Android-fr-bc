package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionMeritListRepository extends JpaRepository<AdmissionMeritList, Long> {
    List<AdmissionMeritList> findByStatus(String status);
    List<AdmissionMeritList> findByAcademicYear(String academicYear);
    List<AdmissionMeritList> findByTestId(Long testId);
    List<AdmissionMeritList> findByCircularId(Long circularId);
    List<AdmissionMeritList> findBySessionIdAndFacultyIdAndDepartmentIdAndProgramId(Long sessionId, Long facultyId, Long departmentId, Long programId);
}
