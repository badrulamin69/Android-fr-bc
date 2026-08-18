package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.GradeRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GradeRuleRepository extends JpaRepository<GradeRule, Long>, JpaSpecificationExecutor<GradeRule> {
    List<GradeRule> findByCourseId(Long courseId);
}
