package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class PdfNoteResponse {
    private Long id;
    private String title;
    private String fileUrl;
    private Long moduleId;
    private String moduleTitle;
}
