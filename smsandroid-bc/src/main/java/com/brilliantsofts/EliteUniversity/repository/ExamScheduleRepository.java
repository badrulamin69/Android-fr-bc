package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Long>, JpaSpecificationExecutor<ExamSchedule> {
}
