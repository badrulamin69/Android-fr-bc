package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AcademicCalendar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AcademicCalendarRepository extends JpaRepository<AcademicCalendar, Long> {

    @Query("SELECT a FROM AcademicCalendar a WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(a.title) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.eventType) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AcademicCalendar> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
