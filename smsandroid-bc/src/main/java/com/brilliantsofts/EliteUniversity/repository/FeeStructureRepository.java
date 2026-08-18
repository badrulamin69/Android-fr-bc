package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.FeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {
    List<FeeStructure> findBySemesterIdAndProgramId(Long semesterId, Long programId);
    List<FeeStructure> findByFeeTypeId(Long feeTypeId);
}
