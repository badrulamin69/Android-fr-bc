package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SemesterRoutineRequest {
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
}
