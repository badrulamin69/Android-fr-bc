package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Curriculum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {

    @Query("SELECT c FROM Curriculum c WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(c.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Curriculum> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
