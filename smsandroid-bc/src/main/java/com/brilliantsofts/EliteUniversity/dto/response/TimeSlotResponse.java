package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TimeSlotResponse {
    private Long id;
    private String name;
    private String code;
    private LocalTime startTime;
    private LocalTime endTime;
    private String slotType;
    private Integer durationMinutes;
    private Integer sortOrder;
    private boolean isActive;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
