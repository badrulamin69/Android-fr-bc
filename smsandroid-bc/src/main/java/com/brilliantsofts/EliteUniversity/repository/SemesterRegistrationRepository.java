package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.SemesterRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SemesterRegistrationRepository extends JpaRepository<SemesterRegistration, Long>, JpaSpecificationExecutor<SemesterRegistration> {
}
