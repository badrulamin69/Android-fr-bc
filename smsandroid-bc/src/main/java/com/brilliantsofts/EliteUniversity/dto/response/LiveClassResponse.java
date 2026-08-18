package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LiveClassResponse {
    private Long id;
    private String title;
    private String meetingUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long moduleId;
    private String moduleTitle;
    private Long teacherId;
    private String teacherName;
}
