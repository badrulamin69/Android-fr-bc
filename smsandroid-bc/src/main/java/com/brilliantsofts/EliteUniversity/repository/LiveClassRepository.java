package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.LiveClass;

import java.util.List;

public interface LiveClassRepository extends org.springframework.data.jpa.repository.JpaRepository<LiveClass, Long> {
    List<LiveClass> findByModuleId(Long moduleId);
    List<LiveClass> findByTeacherId(Long teacherId);
}
