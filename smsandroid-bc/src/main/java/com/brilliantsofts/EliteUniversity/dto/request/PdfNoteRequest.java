package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class PdfNoteRequest {
    private String title;
    private String fileUrl;
    private Long moduleId;
}
