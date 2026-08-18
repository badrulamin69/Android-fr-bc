package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_merit_lists")
@Data
public class AdmissionMeritList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String academicYear;

    private Long sessionId;

    private Long facultyId;

    private Long departmentId;

    private Long programId;

    private String shift;

    private String quotaType;

    private Long testId;

    private Long circularId;

    private String status;

    private Integer totalSeats;

    private Integer totalApplicants;

    private Integer selectedCount;

    private Integer waitingCount;

    private Double cutoffScore;

    private LocalDateTime publishedAt;

    private String publishedBy;

    @Column(columnDefinition = "TEXT")
    private String remarks;

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
