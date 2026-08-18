package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TeacherPublicationRequest {
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
}
