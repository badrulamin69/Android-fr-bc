package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class VideoLectureResponse {
    private Long id;
    private String title;
    private String videoUrl;
    private Integer durationMinutes;
    private Long moduleId;
    private String moduleTitle;
}
