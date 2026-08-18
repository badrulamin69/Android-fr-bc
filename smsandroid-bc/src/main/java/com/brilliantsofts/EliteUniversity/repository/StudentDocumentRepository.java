package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.StudentDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentDocumentRepository extends JpaRepository<StudentDocument, Long> {
    Page<StudentDocument> findByStudentId(Long studentId, Pageable pageable);

    Page<StudentDocument> findByDocumentType(String documentType, Pageable pageable);

    Page<StudentDocument> findByStatus(String status, Pageable pageable);

    @Query("SELECT d FROM StudentDocument d WHERE " +
            "LOWER(d.documentType) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.documentName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(d.status) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<StudentDocument> search(@Param("search") String search, Pageable pageable);

    long countByStatus(String status);
}
