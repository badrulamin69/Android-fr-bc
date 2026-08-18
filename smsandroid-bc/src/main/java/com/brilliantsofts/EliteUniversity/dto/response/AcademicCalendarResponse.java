package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AcademicCalendarResponse {
    private Long id;
    private String uniqueCode;
    private String title;
    private String description;
    private String eventType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long semesterId;
    private boolean isHoliday;
    private boolean isPublished;
    private String color;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
