package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class AnnouncementRequest {
    private String title;
    private String content;
    private String type;
    private Long postedBy;
    private Boolean isActive;
}
