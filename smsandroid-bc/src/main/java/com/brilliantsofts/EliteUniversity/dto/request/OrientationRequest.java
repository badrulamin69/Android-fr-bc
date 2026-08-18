package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OrientationRequest {
    private String title;
    private String description;
    private LocalDate scheduledDate;
    private String venue;
    private Long semesterId;
    private Long academicSessionId;
    private String status;
    private Boolean isActive;
}
