package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ClassRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClassRoutineRepository extends JpaRepository<ClassRoutine, Long> {

    List<ClassRoutine> findBySemesterIdAndSectionId(Long semesterId, Long sectionId);

    List<ClassRoutine> findBySemesterId(Long semesterId);

    @Query("SELECT cr FROM ClassRoutine cr WHERE " +
            "(:semesterId IS NULL OR cr.semesterId = :semesterId) AND " +
            "(:sectionId IS NULL OR cr.sectionId = :sectionId) AND " +
            "(:dayOfWeek IS NULL OR cr.dayOfWeek = :dayOfWeek)")
    List<ClassRoutine> findAllFiltered(@Param("semesterId") Long semesterId,
                                       @Param("sectionId") Long sectionId,
                                       @Param("dayOfWeek") String dayOfWeek);
}
