package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AcademicCalendarRequest {
    private String title;
    private String description;
    private String eventType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long semesterId;
    private boolean isHoliday;
    private boolean isPublished;
    private String color;
}
