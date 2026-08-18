package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.TeacherPromotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherPromotionRepository extends JpaRepository<TeacherPromotion, Long> {

    @Query("SELECT p FROM TeacherPromotion p WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(p.newDesignation) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.previousDesignation) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.approvedByName) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<TeacherPromotion> findAllWithSearch(@Param("search") String search, Pageable pageable);

    Page<TeacherPromotion> findByTeacherId(Long teacherId, Pageable pageable);
}
