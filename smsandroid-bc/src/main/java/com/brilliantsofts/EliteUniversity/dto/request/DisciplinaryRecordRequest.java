package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DisciplinaryRecordRequest {
    private Long studentId;
    private LocalDate incidentDate;
    private String category;
    private String severity;
    private String description;
    private String actionTaken;
    private Long reportedById;
    private String status;
    private String remarks;
}
