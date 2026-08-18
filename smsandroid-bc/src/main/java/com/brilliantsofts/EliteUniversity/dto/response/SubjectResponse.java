package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SubjectResponse {
    private Long id;
    private String uniqueCode;
    private String name;
    private String code;
    private Integer credits;
    private Long courseId;
    private Long departmentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
