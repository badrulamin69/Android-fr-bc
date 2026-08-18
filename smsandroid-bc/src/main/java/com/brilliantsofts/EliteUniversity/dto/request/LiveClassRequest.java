package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LiveClassRequest {
    private String title;
    private String meetingUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long moduleId;
    private Long teacherId;
}
