package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OnlineClassResponse {
    private Long id;
    private String uniqueCode;
    private String title;
    private String description;
    private String meetingUrl;
    private LocalDateTime classDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long courseId;
    private String courseName;
    private Long subjectId;
    private String subjectName;
    private Long administrationId;
    private String administrationName;
    private String recordingUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
