package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ApplicationReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationReviewRepository extends JpaRepository<ApplicationReview, Long> {

    List<ApplicationReview> findByApplicationId(Long applicationId);

    List<ApplicationReview> findByReviewerId(Long reviewerId);

    @Query("SELECT a FROM ApplicationReview a WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<ApplicationReview> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
