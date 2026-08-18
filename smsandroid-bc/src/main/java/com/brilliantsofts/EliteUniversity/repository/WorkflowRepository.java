package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Workflow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {

    @Query("SELECT w FROM Workflow w WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(w.name) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Workflow> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
