package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.EntityComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityCommentRepository extends JpaRepository<EntityComment, Long> {

    Page<EntityComment> findByEntityTypeAndEntityId(String entityType, Long entityId, Pageable pageable);

    long countByEntityTypeAndEntityId(String entityType, Long entityId);
}