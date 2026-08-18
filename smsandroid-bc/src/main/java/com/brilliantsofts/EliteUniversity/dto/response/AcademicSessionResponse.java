package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AcademicSessionResponse {
    private Long id;
    private String sessionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean active;
}
