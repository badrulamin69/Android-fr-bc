package com.brilliantsofts.EliteUniversity.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventRequest {
    private String title;
    private String description;
    private String eventType;
    
    @JsonFormat(pattern = "yyyy-MM-dd[ HH:mm[:ss]][['T']HH:mm[:ss]]")
    private LocalDateTime startDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd[ HH:mm[:ss]][['T']HH:mm[:ss]]")
    private LocalDateTime endDate;
    
    private String venue;
    private Long clubId;
    private Integer maxParticipants;
    private BigDecimal registrationFee;
    private String status;
}
