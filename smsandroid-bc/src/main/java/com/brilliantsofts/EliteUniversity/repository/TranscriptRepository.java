package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Transcript;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TranscriptRepository extends JpaRepository<Transcript, Long> {
    Page<Transcript> findByStudentId(Long studentId, Pageable pageable);

    @Query("SELECT t FROM Transcript t WHERE " +
            "LOWER(t.transcriptNumber) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.status) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.remarks) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Transcript> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
