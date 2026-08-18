package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_confirmations")
@Data
public class AdmissionConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String confirmationNumber;

    private Long allocationId;

    private Long registrationId;

    private String status;

    private Boolean documentsSubmitted;

    private Boolean documentsVerified;

    private Long documentsVerifiedBy;

    private LocalDateTime documentsVerifiedAt;

    private String documentRemarks;

    private Boolean feePaid;

    private Double feeAmount;

    private String feePaymentMethod;

    private String feeTransactionId;

    private LocalDateTime feePaidAt;

    private LocalDateTime confirmedAt;

    private Long confirmedBy;

    private String remarks;

    private Long sessionId;

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
