package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Examination;

import java.util.List;

public interface ExaminationRepository extends org.springframework.data.jpa.repository.JpaRepository<Examination, Long> {
    List<Examination> findByCourseId(Long courseId);
    List<Examination> findBySemester(String semester);
}
