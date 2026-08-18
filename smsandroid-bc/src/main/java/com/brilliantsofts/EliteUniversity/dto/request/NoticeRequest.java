package com.brilliantsofts.EliteUniversity.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.brilliantsofts.EliteUniversity.enums.NoticeAudience;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NoticeRequest {
    private String title;
    private String content;
    private String attachmentUrl;
    
    @JsonFormat(pattern = "yyyy-MM-dd[ HH:mm[:ss]][['T']HH:mm[:ss]]")
    private LocalDateTime publishDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd[ HH:mm[:ss]][['T']HH:mm[:ss]]")
    private LocalDateTime expiryDate;
    
    private boolean published;
    private NoticeAudience audience;
    private Long createdById;
    private Long facultyId;
    private Long departmentId;
}
