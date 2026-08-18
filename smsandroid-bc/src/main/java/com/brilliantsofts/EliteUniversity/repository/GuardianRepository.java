package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Guardian;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GuardianRepository extends JpaRepository<Guardian, Long> {
    Page<Guardian> findByStudentId(Long studentId, Pageable pageable);

    @Query("SELECT g FROM Guardian g WHERE " +
            "LOWER(g.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(g.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(g.phone) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(g.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(g.relationship) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Guardian> search(@Param("search") String search, Pageable pageable);

    long countByStudentId(Long studentId);
}
