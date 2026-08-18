package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExaminationResponse {
    private Long id;
    private String examinationName;
    private String semester;
    private Double totalMarks;
    private Double passMarks;
    private LocalDate examinationDate;
    private Long courseId;
    private String courseName;
}
