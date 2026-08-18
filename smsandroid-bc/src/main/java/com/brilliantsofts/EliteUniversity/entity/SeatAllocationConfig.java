package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "seat_allocation_configs")
@Data
public class SeatAllocationConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sessionId;

    private String academicYear;

    private Integer allocationRound;

    private Boolean autoAllocation;

    private Boolean manualAllocation;

    private LocalDateTime allocationStartDate;

    private LocalDateTime allocationEndDate;

    private Integer acceptDeadlineHours;

    private Boolean lockAfterPublish;

    private Boolean enableQuota;

    private Boolean enableReservedSeats;

    private String status;

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
