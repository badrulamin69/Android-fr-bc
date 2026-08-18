package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.EntityAttachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntityAttachmentRepository extends JpaRepository<EntityAttachment, Long> {

    Page<EntityAttachment> findByEntityTypeAndEntityId(String entityType, Long entityId, Pageable pageable);

    long countByEntityTypeAndEntityId(String entityType, Long entityId);

    @Query("SELECT a FROM EntityAttachment a WHERE " +
            "(:search IS NULL OR :search = '' OR " +
            "LOWER(a.originalFilename) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(a.contentType) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<EntityAttachment> findAllWithSearch(@Param("search") String search, Pageable pageable);
}