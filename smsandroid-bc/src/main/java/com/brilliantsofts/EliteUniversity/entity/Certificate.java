package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "certificates")
@Data
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;

    private String certificateNumber;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String certificateType;

    private LocalDateTime issuedAt;

    private LocalDate validUntil;

    private String status;

    private String purpose;

    @ManyToOne
    @JoinColumn(name = "issued_by_id")
    private User issuedBy;

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
