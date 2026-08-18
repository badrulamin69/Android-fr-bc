package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "program_seat_configs")
@Data
public class ProgramSeatConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long configId;

    private Long facultyId;

    private Long departmentId;

    private Long programId;

    private String shift;

    private Integer totalSeats;

    private Integer generalSeats;

    private Integer quotaSeats;

    private Integer reservedSeats;

    private Integer allocatedSeats;

    private Integer waitingSeats;

    private Boolean isActive;

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
