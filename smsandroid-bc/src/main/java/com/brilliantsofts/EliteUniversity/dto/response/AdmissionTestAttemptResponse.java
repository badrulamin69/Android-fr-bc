package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdmissionTestAttemptResponse {
    private Long id;
    private Long registrationId;
    private Long testId;
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
    private LocalDateTime createdAt;
}
