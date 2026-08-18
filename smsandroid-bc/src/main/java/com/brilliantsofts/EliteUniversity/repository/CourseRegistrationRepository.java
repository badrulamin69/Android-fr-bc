package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long>, JpaSpecificationExecutor<CourseRegistration> {
}
