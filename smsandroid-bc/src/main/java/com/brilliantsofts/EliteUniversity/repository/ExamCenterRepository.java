package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ExamCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExamCenterRepository extends JpaRepository<ExamCenter, Long>, JpaSpecificationExecutor<ExamCenter> {
}
