package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class VideoLectureRequest {
    private String title;
    private String videoUrl;
    private Integer durationMinutes;
    private Long moduleId;
}
