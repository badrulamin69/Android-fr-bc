package com.brilliantsofts.EliteUniversity.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "admission_test_attempts")
@Data
public class AdmissionTestAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long registrationId;

    private Long testId;

    @Column(columnDefinition = "TEXT")
    private String answers;

    private Integer totalQuestions;

    private Integer correctAnswers;

    private Double score;

    private Double maxScore;

    private Double percentage;

    private Integer timeTakenSeconds;

    private LocalDateTime startedAt;

    private LocalDateTime submittedAt;

    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
