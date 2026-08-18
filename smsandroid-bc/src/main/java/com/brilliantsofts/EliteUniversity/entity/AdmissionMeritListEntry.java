package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_merit_list_entries")
@Data
public class AdmissionMeritListEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long meritListId;

    private Long registrationId;

    @Column(name = "`rank`")
    private Integer rank;

    private String rollNumber;

    private String applicationNumber;

    private String applicantName;

    private String facultyName;

    private String departmentName;

    private String programName;

    private String shift;

    private Double testMarks;

    private Double testMaxMarks;

    private Double score;

    private Double academicScore;

    private Double totalWeightedScore;

    private Double sscGpa;

    private Double hscGpa;

    private String quotaType;

    private String status;

    private Boolean isOffered;

    private Boolean isEnrolled;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    private LocalDateTime submittedAt;

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
