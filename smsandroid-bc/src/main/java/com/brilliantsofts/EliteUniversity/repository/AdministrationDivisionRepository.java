package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdministrationDivision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministrationDivisionRepository extends JpaRepository<AdministrationDivision, Long> {

    @Query("SELECT ad FROM AdministrationDivision ad WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(ad.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(ad.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdministrationDivision> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
