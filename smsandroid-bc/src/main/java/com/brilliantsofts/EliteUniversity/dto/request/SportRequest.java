package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class SportRequest {
    private String name;
    private String code;
    private String description;
    private String coachName;
    private Integer maxParticipants;
    private boolean isActive;
}
