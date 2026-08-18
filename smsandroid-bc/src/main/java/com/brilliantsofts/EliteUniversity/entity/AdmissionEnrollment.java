package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_enrollments")
@Data
public class AdmissionEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;

    private String enrollmentNumber;

    private Long applicationId;

    private Long studentId;

    private Long offerLetterId;

    private Long programId;

    private Long departmentId;

    private Long facultyId;

    private Long semesterId;

    private Long batchId;

    private Long sectionId;

    private String status;

    private LocalDateTime enrolledAt;

    private String remarks;

    private Boolean isDocumentVerified;

    private Boolean isFeePaid;

    private Double totalFeePaid;

    private Long enrolledById;

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
