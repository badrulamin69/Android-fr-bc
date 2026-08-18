package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "entity_attachments")
@Data
public class EntityAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private Long entityId;

    private String originalFilename;

    private String storedFilename;

    private String path;

    private String contentType;

    private Long size;

    private Long uploadedById;

    private String category;

    private boolean verified;

    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}