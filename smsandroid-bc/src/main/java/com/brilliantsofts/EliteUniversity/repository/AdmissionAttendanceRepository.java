package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionAttendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdmissionAttendanceRepository extends JpaRepository<AdmissionAttendance, Long> {

    List<AdmissionAttendance> findByTestId(Long testId);

    List<AdmissionAttendance> findByRegistrationId(Long registrationId);

    @Query("SELECT a FROM AdmissionAttendance a WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdmissionAttendance> search(@Param("search") String search, Pageable pageable);

    long countByTestIdAndStatus(Long testId, String status);

    long countByTestId(Long testId);
}
