package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CurriculumResponse {
    private Long id;
    private String uniqueCode;
    private Long programId;
    private Long subjectId;
    private Long semesterId;
    private boolean isRequired;
    private Integer orderNo;
    private Integer creditHours;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
