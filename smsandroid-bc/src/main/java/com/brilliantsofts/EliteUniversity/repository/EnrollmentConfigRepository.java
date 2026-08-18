package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.EnrollmentConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentConfigRepository extends JpaRepository<EnrollmentConfig, Long> {
    List<EnrollmentConfig> findByActiveTrue();
    Optional<EnrollmentConfig> findBySemesterId(Long semesterId);
}
