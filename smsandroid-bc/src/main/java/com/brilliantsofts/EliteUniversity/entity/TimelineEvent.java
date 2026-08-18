package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "timeline_events")
@Data
public class TimelineEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private Long entityId;

    private Long userId;

    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String oldValue;

    private String newValue;

    private String ipAddress;

    private String severity;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
