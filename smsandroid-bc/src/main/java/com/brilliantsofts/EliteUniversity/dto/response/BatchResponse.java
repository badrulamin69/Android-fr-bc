package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BatchResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private Integer startYear;
    private Integer endYear;
    private Long courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
