package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.StudentPromotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentPromotionRepository extends JpaRepository<StudentPromotion, Long> {
    Page<StudentPromotion> findByStudentId(Long studentId, Pageable pageable);

    @Query("SELECT p FROM StudentPromotion p WHERE " +
            "LOWER(p.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.remarks) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<StudentPromotion> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
