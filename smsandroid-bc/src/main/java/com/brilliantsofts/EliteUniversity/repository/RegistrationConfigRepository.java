package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.RegistrationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RegistrationConfigRepository extends JpaRepository<RegistrationConfig, Long> {
    List<RegistrationConfig> findByIsActiveTrue();
    Optional<RegistrationConfig> findBySemesterId(Long semesterId);
}
