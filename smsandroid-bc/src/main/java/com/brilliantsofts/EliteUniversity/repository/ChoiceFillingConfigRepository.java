package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.ChoiceFillingConfig;

import java.util.List;
import java.util.Optional;

public interface ChoiceFillingConfigRepository extends org.springframework.data.jpa.repository.JpaRepository<ChoiceFillingConfig, Long> {
    List<ChoiceFillingConfig> findByIsActiveTrue();
    Optional<ChoiceFillingConfig> findByIsActiveTrueAndIdNot(Long id);
    Optional<ChoiceFillingConfig> findTopByIsActiveTrueOrderByIdDesc();
    List<ChoiceFillingConfig> findBySessionId(Long sessionId);
    List<ChoiceFillingConfig> findByStatus(String status);
}
