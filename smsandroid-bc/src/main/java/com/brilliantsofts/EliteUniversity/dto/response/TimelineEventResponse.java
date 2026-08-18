package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TimelineEventResponse {
    private Long id;
    private String entityType;
    private Long entityId;
    private Long userId;
    private String eventType;
    private String description;
    private String oldValue;
    private String newValue;
    private String ipAddress;
    private String severity;
    private LocalDateTime createdAt;
}
