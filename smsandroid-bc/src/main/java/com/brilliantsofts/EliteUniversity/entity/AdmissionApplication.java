package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_applications")
@Data
public class AdmissionApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;

    private String applicationNumber;

    private Long candidateId;

    private Long circularId;

    private Long sessionId;

    private Long programId;

    private Long departmentId;

    private Long campusId;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    private LocalDateTime submittedAt;

    private Boolean isSubmitted;

    private Boolean isVerified;

    private Long examId;

    private Double testScore;

    private Double meritScore;

    private Integer meritPosition;

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
