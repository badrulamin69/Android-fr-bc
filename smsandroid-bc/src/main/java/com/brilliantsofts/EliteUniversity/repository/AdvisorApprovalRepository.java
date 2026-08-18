package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.AdvisorApproval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvisorApprovalRepository extends JpaRepository<AdvisorApproval, Long> {
    List<AdvisorApproval> findByRegistrationSemesterIdAndRegistrationStatus(Long semesterId, String status);
}
