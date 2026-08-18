package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionCampaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdmissionCampaignRepository extends JpaRepository<AdmissionCampaign, Long> {

    @Query("SELECT a FROM AdmissionCampaign a WHERE " +
           "(:search IS NULL OR :search = '' OR " +
           "LOWER(a.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.type) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(a.uniqueCode) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<AdmissionCampaign> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
