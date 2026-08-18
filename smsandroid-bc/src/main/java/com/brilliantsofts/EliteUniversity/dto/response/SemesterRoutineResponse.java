package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SemesterRoutineResponse {
    private Long id;
    private String uniqueCode;
    private Long semesterId;
    private Long programId;
    private Long batchId;
    private String description;
    private Integer totalWeeks;
    private Integer midtermWeek;
    private Integer finalExamWeek;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
