package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SemesterRequest {
    private String name;
    private String code;
    private Long academicSessionId;
    private Integer orderNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate registrationDeadline;
    private String status;
    private boolean isActive;
}
