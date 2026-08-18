package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.StudentProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    @Query("SELECT s FROM StudentProfile s WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(s.city) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.nationality) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<StudentProfile> findAllWithSearch(@Param("search") String search, Pageable pageable);

    java.util.Optional<StudentProfile> findByStudentId(Long studentId);
}
