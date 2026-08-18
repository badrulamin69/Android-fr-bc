package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long>, JpaSpecificationExecutor<CourseMaterial> {
}
