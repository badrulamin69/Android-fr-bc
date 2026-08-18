package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "department_allocations")
@Data
public class DepartmentAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String allocationNumber;

    private Integer meritRank;

    private Double totalScore;

    private String status;

    private LocalDateTime allocatedAt;

    private LocalDateTime confirmedAt;

    private String remarks;

    private Long registrationId;

    private Long allocatedProgramId;

    private Long allocatedDepartmentId;

    private Long allocatedBatchId;

    private Long allocatedSectionId;

    private Long semesterId;

    private Long allocatedById;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}