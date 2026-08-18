package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.SeatAllocationConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatAllocationConfigRepository extends JpaRepository<SeatAllocationConfig, Long> {

    List<SeatAllocationConfig> findBySessionId(Long sessionId);

    List<SeatAllocationConfig> findByStatus(String status);

    @Query("SELECT s FROM SeatAllocationConfig s WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(s.academicYear) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<SeatAllocationConfig> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
