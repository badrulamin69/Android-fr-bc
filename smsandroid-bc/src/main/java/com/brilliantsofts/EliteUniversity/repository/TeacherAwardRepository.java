package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.TeacherAward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherAwardRepository extends JpaRepository<TeacherAward, Long> {

    @Query("SELECT a FROM TeacherAward a WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(a.awardName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.awardingBody) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.category) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<TeacherAward> findAllWithSearch(@Param("search") String search, Pageable pageable);

    Page<TeacherAward> findByTeacherId(Long teacherId, Pageable pageable);
}
