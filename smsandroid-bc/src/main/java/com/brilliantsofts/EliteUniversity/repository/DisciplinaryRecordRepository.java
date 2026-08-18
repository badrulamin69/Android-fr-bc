package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.DisciplinaryRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DisciplinaryRecordRepository extends JpaRepository<DisciplinaryRecord, Long> {
    Page<DisciplinaryRecord> findByStudentId(Long studentId, Pageable pageable);

    @Query("SELECT d FROM DisciplinaryRecord d WHERE " +
            "LOWER(d.category) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.severity) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.description) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.status) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<DisciplinaryRecord> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
