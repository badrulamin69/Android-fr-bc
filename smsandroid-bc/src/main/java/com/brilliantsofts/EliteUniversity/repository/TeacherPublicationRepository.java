package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.TeacherPublication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherPublicationRepository extends JpaRepository<TeacherPublication, Long> {

    @Query("SELECT p FROM TeacherPublication p WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.authors) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(p.journal) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<TeacherPublication> findAllWithSearch(@Param("search") String search, Pageable pageable);

    Page<TeacherPublication> findByTeacherId(Long teacherId, Pageable pageable);
}
