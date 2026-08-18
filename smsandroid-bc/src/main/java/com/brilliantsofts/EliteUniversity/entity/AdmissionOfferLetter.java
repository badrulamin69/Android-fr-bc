package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_offer_letters")
@Data
public class AdmissionOfferLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;

    private String letterNumber;

    private Long applicationId;

    private Long meritListEntryId;

    private LocalDateTime issuedAt;

    private LocalDateTime validUntil;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String letterContent;

    private String conditions;

    private String remarks;

    private Long issuedById;

    private LocalDateTime acceptedAt;

    private LocalDateTime declinedAt;

    private String declineReason;

    private Boolean isDownloaded;

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
