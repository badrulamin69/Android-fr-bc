package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class TimelineEventRequest {
    private String entityType;
    private Long entityId;
    private Long userId;
    private String eventType;
    private String description;
    private String oldValue;
    private String newValue;
    private String ipAddress;
    private String severity;
}
