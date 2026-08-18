package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "medical_info")
@Data
public class MedicalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueCode;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String bloodGroup;

    private Double height;

    private Double weight;

    private String allergies;

    private String medications;

    private String conditions;

    private String emergencyContact;

    private String emergencyPhone;

    private String insuranceProvider;

    private String insuranceNumber;

    private String doctorName;

    private String doctorPhone;

    private String notes;

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
