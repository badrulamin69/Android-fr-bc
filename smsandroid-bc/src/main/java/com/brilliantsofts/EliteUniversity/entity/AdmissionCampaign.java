package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "admission_campaigns")
@Data
public class AdmissionCampaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;

    private String name;

    private String type;

    private String description;

    private Double budget;

    private Double spent;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status;

    private String targetAudience;

    private String channels;

    private Integer applicationsGenerated;

    private Integer enrollmentsConverted;

    private String notes;

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
