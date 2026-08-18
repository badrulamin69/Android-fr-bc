package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.StudentIdRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentIdRecordRepository extends JpaRepository<StudentIdRecord, Long> {

    @Query("SELECT s FROM StudentIdRecord s WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(s.studentCode) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(s.idNumber) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<StudentIdRecord> findAllWithSearch(@Param("search") String search, Pageable pageable);

    Page<StudentIdRecord> findByStatus(String status, Pageable pageable);
}
