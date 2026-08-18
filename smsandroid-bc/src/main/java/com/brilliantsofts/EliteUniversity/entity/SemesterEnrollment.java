package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "semester_enrollments")
@Data
public class SemesterEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enrollmentNumber;

    private Long studentId;

    private Long semesterId;

    private Long batchId;

    private Long programId;

    private Long facultyId;

    private Long departmentId;

    private Long advisorId;

    private LocalDateTime enrollmentDate;

    private String status;

    private Integer registeredCredits;

    private Integer minCredits;

    private Integer maxCredits;

    private String advisorStatus;

    private String advisorComments;

    private LocalDateTime advisorApprovedAt;

    private String paymentStatus;

    private BigDecimal paymentAmount;

    private String paymentReference;

    private LocalDateTime paymentDate;

    private boolean isFinalized;

    private LocalDateTime finalizedAt;

    private String remarks;

    private boolean isActive;

    private boolean isLateEnrollment;

    private String enrollmentType;

    private LocalDateTime cancelledAt;

    private String cancellationReason;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
