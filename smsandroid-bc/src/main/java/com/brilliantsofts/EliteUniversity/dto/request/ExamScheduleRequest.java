package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamScheduleRequest {
    private Long examId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String venue;
    private String notes;
}
