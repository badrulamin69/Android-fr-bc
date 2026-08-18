package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionInterview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdmissionInterviewRepository extends JpaRepository<AdmissionInterview, Long> {

    List<AdmissionInterview> findByApplicationId(Long applicationId);

    List<AdmissionInterview> findByInterviewerId(Long interviewerId);

    @Query("SELECT a FROM AdmissionInterview a WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(a.interviewType) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdmissionInterview> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
