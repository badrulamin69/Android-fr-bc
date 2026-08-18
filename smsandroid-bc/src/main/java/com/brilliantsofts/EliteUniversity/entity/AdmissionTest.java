package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_tests")
@Data
public class AdmissionTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String academicYear;

    private Long sessionId;

    private Long facultyId;

    private Long departmentId;

    private Long programId;

    private String shift;

    private String testType;

    private LocalDateTime testDate;

    private String startTime;

    private String endTime;

    private Integer durationMinutes;

    private Integer totalMarks;

    private Integer passingMarks;

    private Boolean negativeMarking;

    private Double negativeMarkValue;

    private String examCenter;

    private String building;

    private String room;

    private Integer seatCapacity;

    @Column(columnDefinition = "TEXT")
    private String instructions;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String status;

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
