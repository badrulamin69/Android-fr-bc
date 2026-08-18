package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_test_results")
@Data
public class AdmissionTestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double writtenMarks;

    private Double mcqMarks;

    private Double vivaMarks;

    private Double writtenMax;

    private Double mcqMax;

    private Double vivaMax;

    private Double totalWeightedScore;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    private Long registrationId;

    private Long testId;

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
