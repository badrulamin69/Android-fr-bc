package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "admission_waiting_list_entries")
@Data
public class AdmissionWaitingListEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long waitingListId;

    private Long registrationId;

    @Column(name = "`rank`")
    private Integer rank;

    private String rollNumber;

    private String applicationNumber;

    private String applicantName;

    private Double score;

    private Double testMarks;

    private Double totalWeightedScore;

    private String status;

    private Boolean isPromoted;

    private Boolean isOffered;

    @Column(columnDefinition = "TEXT")
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
