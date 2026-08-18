package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TeacherPublicationResponse {
    private Long id;
    private String uniqueCode;
    private Long teacherId;
    private String title;
    private String authors;
    private String journal;
    private String publicationType;
    private LocalDate publicationDate;
    private String volume;
    private String issue;
    private String pages;
    private String doi;
    private String isbn;
    private String abstractText;
    private String url;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
