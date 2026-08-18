package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_interviews")
@Data
public class AdmissionInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;

    private Long applicationId;

    private Long interviewerId;

    private LocalDateTime scheduledAt;

    private LocalDateTime completedAt;

    private String interviewType;

    private String status;

    private String remarks;

    private Double score;

    private Double maxScore;

    private String strengths;

    private String weaknesses;

    private Boolean isRecommended;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
