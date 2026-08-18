package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionEnrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdmissionEnrollmentRepository extends JpaRepository<AdmissionEnrollment, Long> {

    List<AdmissionEnrollment> findByApplicationId(Long applicationId);

    List<AdmissionEnrollment> findByStudentId(Long studentId);

    List<AdmissionEnrollment> findByProgramId(Long programId);

    @Query("SELECT a FROM AdmissionEnrollment a WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(a.enrollmentNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdmissionEnrollment> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
