package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DisciplinaryRecordResponse {
    private Long id;
    private String uniqueCode;
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
