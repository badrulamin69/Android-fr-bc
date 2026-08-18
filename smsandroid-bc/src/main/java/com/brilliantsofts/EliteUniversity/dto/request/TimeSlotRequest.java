package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class TimeSlotRequest {
    private String name;
    private String code;
    private String startTime;
    private String endTime;
    private String slotType;
    private Integer durationMinutes;
    private Integer sortOrder;
    private boolean isActive;
    private String remarks;
}
