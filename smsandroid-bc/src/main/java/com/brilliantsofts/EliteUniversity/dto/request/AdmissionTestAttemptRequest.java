package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdmissionTestAttemptRequest {
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
}
