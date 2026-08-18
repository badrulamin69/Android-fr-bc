package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.SeatAllocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatAllocationRepository extends JpaRepository<SeatAllocation, Long> {

    List<SeatAllocation> findByTestId(Long testId);

    List<SeatAllocation> findByRegistrationId(Long registrationId);

    @Query("SELECT s FROM SeatAllocation s WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(s.seatNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.rollNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(s.centerName) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<SeatAllocation> search(@Param("search") String search, Pageable pageable);

    long countByTestId(Long testId);
}
