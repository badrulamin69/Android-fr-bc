package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExaminationRequest {
    private String examinationName;
    private String semester;
    private Double totalMarks;
    private Double passMarks;
    private LocalDate examinationDate;
    private Long courseId;
}
