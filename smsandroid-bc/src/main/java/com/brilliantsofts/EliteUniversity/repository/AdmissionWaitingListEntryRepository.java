package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdmissionWaitingListEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionWaitingListEntryRepository extends JpaRepository<AdmissionWaitingListEntry, Long> {
    List<AdmissionWaitingListEntry> findByWaitingListId(Long waitingListId);
    List<AdmissionWaitingListEntry> findByWaitingListIdOrderByRankAsc(Long waitingListId);
    List<AdmissionWaitingListEntry> findByWaitingListIdAndStatus(Long waitingListId, String status);
}
