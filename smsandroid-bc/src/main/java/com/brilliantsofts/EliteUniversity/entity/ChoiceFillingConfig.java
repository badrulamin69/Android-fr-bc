package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "choice_filling_configs")
@Data
public class ChoiceFillingConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sessionId;

    private LocalDateTime choiceStartDate;

    private LocalDateTime choiceEndDate;

    private Integer maxChoices;

    private Integer minChoices;

    private Boolean allowEditingBeforeDeadline;

    private Boolean autoLockAfterDeadline;

    private Boolean includeWaitingList;

    private String status;

    private String remarks;

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
