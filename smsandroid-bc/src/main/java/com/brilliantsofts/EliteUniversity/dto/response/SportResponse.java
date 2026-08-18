package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SportResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private String description;
    private String coachName;
    private Integer maxParticipants;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
