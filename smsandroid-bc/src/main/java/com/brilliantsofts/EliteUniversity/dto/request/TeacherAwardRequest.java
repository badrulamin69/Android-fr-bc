package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TeacherAwardRequest {
    private Long teacherId;
    private String awardName;
    private String awardingBody;
    private String category;
    private LocalDate awardDate;
    private String description;
    private String grade;
    private String status;
}
