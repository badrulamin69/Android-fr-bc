package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.SemesterRoutine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SemesterRoutineRepository extends JpaRepository<SemesterRoutine, Long> {

    @Query("SELECT sr FROM SemesterRoutine sr WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(sr.description) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<SemesterRoutine> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
