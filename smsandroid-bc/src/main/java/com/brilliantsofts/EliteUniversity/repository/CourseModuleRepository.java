package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.CourseModule;

import java.util.List;

public interface CourseModuleRepository extends org.springframework.data.jpa.repository.JpaRepository<CourseModule, Long> {
    List<CourseModule> findByCourseId(Long courseId);
}
