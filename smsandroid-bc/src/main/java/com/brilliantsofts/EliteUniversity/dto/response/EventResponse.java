package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventResponse {
    private Long id;
    private String uniqueCode;
    private String title;
    private String description;
    private String eventType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String venue;
    private Long clubId;
    private Integer maxParticipants;
    private BigDecimal registrationFee;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
