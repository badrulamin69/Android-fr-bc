package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventRegistrationRequest {
    private Long eventId;
    private Long studentId;
    private LocalDateTime registrationDate;
    private String status;
    private String remarks;
}
