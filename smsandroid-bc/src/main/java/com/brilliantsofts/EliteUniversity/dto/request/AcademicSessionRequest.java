package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AcademicSessionRequest {
    private String sessionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
