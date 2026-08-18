package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Enrollment;

import java.util.List;

public interface EnrollmentRepository extends org.springframework.data.jpa.repository.JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    List<Enrollment> findByCourseId(Long courseId);
    List<Enrollment> findByStudentIdAndSemester(Long studentId, String semester);
}
