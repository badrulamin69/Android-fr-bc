package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "registration_configs")
@Data
public class RegistrationConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long semesterId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer minCredits;

    private Integer maxCredits;

    private boolean allowAddDrop;

    private LocalDateTime addDropDeadline;

    private boolean advisorApprovalRequired;

    private boolean paymentRequired;

    private boolean isActive;

    private boolean isClosed;

    private String status;

    private String remarks;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
