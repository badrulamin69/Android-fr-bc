package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Hostel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HostelRepository extends JpaRepository<Hostel, Long> {

    @Query("SELECT h FROM Hostel h WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(h.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(h.code) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Hostel> findAllWithSearch(@Param("search") String search, Pageable pageable);
}
