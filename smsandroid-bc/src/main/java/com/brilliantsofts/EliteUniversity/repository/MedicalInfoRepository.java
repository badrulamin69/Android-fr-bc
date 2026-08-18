package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.MedicalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedicalInfoRepository extends JpaRepository<MedicalInfo, Long> {
    Optional<MedicalInfo> findByStudentId(Long studentId);

    Page<MedicalInfo> findByStudentId(Long studentId, Pageable pageable);

    @Query("SELECT m FROM MedicalInfo m WHERE " +
            "LOWER(m.bloodGroup) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(m.allergies) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(m.conditions) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(m.doctorName) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<MedicalInfo> search(@Param("search") String search, Pageable pageable);

    long countByBloodGroup(String bloodGroup);
}
