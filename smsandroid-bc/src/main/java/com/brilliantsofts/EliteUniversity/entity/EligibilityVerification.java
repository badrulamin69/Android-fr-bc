package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "eligibility_verifications")
@Data
public class EligibilityVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long registrationId;

    private Long testId;

    private String status;

    private String verifiedBy;

    private LocalDateTime verifiedAt;

    private String remarks;

    private boolean sscGpaVerified;

    private boolean hscGpaVerified;

    private boolean documentsVerified;

    @CreationTimestamp
    private LocalDateTime createdAt;
}