package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TeacherAwardResponse {
    private Long id;
    private String uniqueCode;
    private Long teacherId;
    private String awardName;
    private String awardingBody;
    private String category;
    private LocalDate awardDate;
    private String description;
    private String grade;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
