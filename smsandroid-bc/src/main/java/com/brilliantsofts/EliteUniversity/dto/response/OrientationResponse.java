package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class OrientationResponse {
    private Long id;
    private String uniqueCode;
    private String title;
    private String description;
    private LocalDate scheduledDate;
    private String venue;
    private Long semesterId;
    private Long academicSessionId;
    private String status;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
