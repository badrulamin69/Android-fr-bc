package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Prerequisite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrerequisiteRepository extends JpaRepository<Prerequisite, Long> {

    @Query("SELECT p FROM Prerequisite p WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(p.minGrade) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Prerequisite> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
