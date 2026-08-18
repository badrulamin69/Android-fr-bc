package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OnlineClassRequest {
    private String title;
    private String description;
    private String meetingUrl;
    private LocalDateTime classDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long courseId;
    private Long subjectId;
    private Long administrationId;
    private String recordingUrl;
}
