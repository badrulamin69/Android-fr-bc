package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.LeaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("SELECT l FROM LeaveRequest l WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(l.leaveType) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(l.reason) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(l.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<LeaveRequest> findAllWithSearch(@Param("search") String search, Pageable pageable);

    Page<LeaveRequest> findByEmployeeId(Long employeeId, Pageable pageable);
}
