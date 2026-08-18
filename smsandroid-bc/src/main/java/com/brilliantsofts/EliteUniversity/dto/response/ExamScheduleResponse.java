package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamScheduleResponse {
    private Long id;
    private String uniqueCode;
    private Long examId;
    private String examName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String venue;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
