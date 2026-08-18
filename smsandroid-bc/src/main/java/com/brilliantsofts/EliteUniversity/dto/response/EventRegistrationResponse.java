package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventRegistrationResponse {
    private Long id;
    private String uniqueCode;
    private Long eventId;
    private String eventTitle;
    private Long studentId;
    private String studentName;
    private LocalDateTime registrationDate;
    private String status;
    private String remarks;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
