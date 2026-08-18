package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionTestAttempt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdmissionTestAttemptRepository extends JpaRepository<AdmissionTestAttempt, Long> {

    @Query("SELECT a FROM AdmissionTestAttempt a WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdmissionTestAttempt> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
