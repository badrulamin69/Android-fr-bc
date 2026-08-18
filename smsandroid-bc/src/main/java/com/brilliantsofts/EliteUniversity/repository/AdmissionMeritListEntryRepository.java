package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionMeritListEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdmissionMeritListEntryRepository extends JpaRepository<AdmissionMeritListEntry, Long> {
    List<AdmissionMeritListEntry> findByMeritListId(Long meritListId);
    Page<AdmissionMeritListEntry> findByMeritListId(Long meritListId, Pageable pageable);
    List<AdmissionMeritListEntry> findByMeritListIdOrderByRankAsc(Long meritListId);
    List<AdmissionMeritListEntry> findByMeritListIdAndStatus(Long meritListId, String status);

    @Query("SELECT e FROM AdmissionMeritListEntry e WHERE e.meritListId = :meritListId AND " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(e.applicantName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(e.rollNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(e.applicationNumber) LIKE LOWER(CONCAT('%', :search, '%'))) AND " +
            "(:status IS NULL OR :status = '' OR e.status = :status)")
    Page<AdmissionMeritListEntry> findByMeritListIdWithFilter(
            @Param("meritListId") Long meritListId,
            @Param("search") String search,
            @Param("status") String status,
            Pageable pageable);
}
