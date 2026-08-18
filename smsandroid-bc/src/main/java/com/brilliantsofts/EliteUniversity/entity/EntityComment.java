package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "entity_comments")
@Data
public class EntityComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private Long entityId;

    private Long userId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private Long parentId;

    private boolean edited;

    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}