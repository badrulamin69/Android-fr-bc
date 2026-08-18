package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.FeeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeTypeRepository extends JpaRepository<FeeType, Long> {
    List<FeeType> findByIsActiveTrue();
    List<FeeType> findByCategory(String category);
    List<FeeType> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCase(String name, String code);
}
