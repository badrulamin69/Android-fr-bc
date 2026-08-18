package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "program_seat_allocations")
@Data
public class ProgramSeatAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String allocationNumber;

    private Long configId;

    private Integer allocationRound;

    private Integer choiceNumber;

    private Long allocatedFacultyId;

    private String shift;

    private Integer meritRank;

    private BigDecimal totalScore;

    private String status;

    private LocalDateTime allocatedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime declinedAt;

    private LocalDateTime deadline;

    private LocalDateTime confirmedAt;

    private boolean isWaiting;

    private Integer waitingRank;

    private String remarks;

    private Long registrationId;

    private Long allocatedProgramId;

    private Long allocatedDepartmentId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
