package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment_configs")
@Data
public class EnrollmentConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "academic_session_id")
    private AcademicSession academicSession;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime lateEnrollmentDate;

    private Integer minCredits;

    private Integer maxCredits;

    private String enrollmentStatus;

    private boolean active;

    private boolean closed;

    private boolean requiresAdvisorApproval;

    private boolean requiresPayment;

    private boolean allowLateEnrollment;

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
